package com.example.accessingdatamysql;

import com.example.accessingdatamysql.controllers.ResponseObjectForAverageUserScore;
import com.example.accessingdatamysql.repositories.CallTRepository;
import com.example.accessingdatamysql.models.CallT;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller    // This means that this class is a Controller
@RequestMapping(path = "/api") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired

    private CallTRepository callTRepository;

    @GetMapping(path = "/averages")
    public @ResponseBody
    ResponseObjectForAverageUserScore averageUserScore(@RequestParam("user_name") String user, @RequestParam(value = "type", defaultValue = "month") String type) {
        // This returns a JSON or XML with the users
        int totalAverage = callTRepository.getAverageScoreByUser(user);

        LocalDateTime minDate;
        if (type.equals("week")) {
             minDate = LocalDateTime.now().minusDays(7);
        } else {
             minDate = LocalDateTime.now().minusMonths(1);
        }

        List<CallT> scores = new ArrayList<>();

        scores = callTRepository.getAllByUserAndDateGreaterThanEqual(user, minDate);


        return new ResponseObjectForAverageUserScore(totalAverage, scores);
    }

    @GetMapping(path = "/lastCalls")
    public @ResponseBody
    Iterable<CallT> lastFiveCalls(@RequestParam("user_name") String user) {
        // This returns a JSON or XML with the users

        return callTRepository.getAllByUserOrderByDateDescLimit(user);
    }

    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<CallT> getAllUsers() {
        // This returns a JSON or XML with the users

        return callTRepository.distinctUsers();
    }

    @PostMapping(path = "/import")
    public @ResponseBody
    boolean uploadFile(@RequestParam("file") MultipartFile files) throws IOException {
        Workbook workbook = new XSSFWorkbook(files.getInputStream());
        Sheet worksheet = workbook.getSheetAt(0);
        List<CallT> callList = new ArrayList<>();

        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            CallT callT = new CallT();

            Row row = worksheet.getRow(index);

            callT.setUser(row.getCell(0).getStringCellValue());
            callT.setClient(row.getCell(1).getStringCellValue());
            callT.setClientType(row.getCell(2).getStringCellValue());
            callT.setDuration((int) row.getCell(4).getNumericCellValue());
            callT.setTypeOfCall(row.getCell(5).getStringCellValue());
            callT.setExternalCallScore((int) row.getCell(6).getNumericCellValue());
            callT.setDate(row.getCell(3).getLocalDateTimeCellValue().plusMonths(5).plusDays(20));

            callList.add(callT);
        }

        callTRepository.saveAll(callList);
        return true;
    }

    @GetMapping(path = "/normalizedates")
    public @ResponseBody
    boolean updateDatabaseDates(){


        return true;
    }
}

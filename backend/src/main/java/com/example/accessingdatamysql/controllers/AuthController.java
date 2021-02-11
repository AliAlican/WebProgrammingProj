//package com.example.accessingdatamysql.controllers;
//
//
//import com.example.accessingdatamysql.services.AuthService;
//import com.example.accessingdatamysql.models.User;
//import com.example.accessingdatamysql.models.exception.PasswordDoesntMatchException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//@RequestMapping("/auth")
//public class AuthController {
//
//    private final AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping(path = "/signup")
//    public User signUpUser(@RequestParam String username,
//                           @RequestParam String password,
//                           @RequestParam String repeatedPassword) {
//        try {
//            return this.authService.signUpUser(username,password,repeatedPassword);
//        } catch (RuntimeException ex) {
//            throw new PasswordDoesntMatchException();
//        }
//    }
//
//    @PostMapping(path = "/login")
//    public String loginUser(HttpServletRequest req) {
//        String email = req.getParameter("email");
//        if (email != null ) {
//            req.getSession().setAttribute("email", email);
//            return "redirect:/home";
//        } else {
//            return "redirect:/login";
//        }
//    }
//
//}

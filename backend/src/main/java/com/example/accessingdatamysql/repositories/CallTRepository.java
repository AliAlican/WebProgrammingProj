package com.example.accessingdatamysql.repositories;

import com.example.accessingdatamysql.models.CallT;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CallTRepository extends CrudRepository<CallT, Integer> {

    @Query("select c from CallT c group by c.user")
    List<CallT> distinctUsers();

    @Query(nativeQuery = true, value = "select * from CallT where user = :user order by date DESC LIMIT 5")
    List<CallT> getAllByUserOrderByDateDescLimit(String user);

    @Query("select avg(c.externalCallScore) from CallT c where c.user = :user")
    int getAverageScoreByUser(String user);

    List<CallT> getAllByUserAndDateGreaterThanEqual(String user, LocalDateTime minDate);
}

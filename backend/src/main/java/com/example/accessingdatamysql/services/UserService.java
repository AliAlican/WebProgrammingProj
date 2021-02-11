package com.example.accessingdatamysql.services;


import com.example.accessingdatamysql.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User findById(String userId);
    User registerUser(User user);
}

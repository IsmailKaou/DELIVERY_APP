package com.ensa.deliveryapp.controller;

import com.ensa.deliveryapp.model.User;
import com.ensa.deliveryapp.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initAdminUsers(){
        userService.initAdminUsers();
    }


    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "This URL is only accessible to Admin";
    }

    @GetMapping({"/forCustomer"})
    @PreAuthorize("hasRole('CUSTOMER')")
    public String forUser(){
        return "This URL is only accessible to Customer";
    }
}

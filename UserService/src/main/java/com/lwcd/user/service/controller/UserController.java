package com.lwcd.user.service.controller;

import com.lwcd.user.service.entity.User;
import com.lwcd.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    //create User

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.SaveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    //get Single User
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        User user1 = userService.getUser(userId);

        return ResponseEntity.ok(user1);
    }

    //get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user1 = userService.getAllUser();
        return ResponseEntity.ok(user1);
    }


}

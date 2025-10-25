package com.lwcd.user.service.service;

import com.lwcd.user.service.entity.User;

import java.util.List;

public interface UserService {

    //save user
    User SaveUser(User user);

    //get alll user
    List<User> getAllUser();


    //get single user
    User getUser(String userId);

}

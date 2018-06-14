package com.rumian.demo1.controller;

import com.rumian.demo1.model.User;
import com.rumian.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController2 {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(){
        User user = userService.getUser("xianggu");
        return user;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(){
        userService.deleteUser("xianggu");
        return "执行了删除";
    }
}

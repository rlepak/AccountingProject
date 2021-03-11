package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/registration")
    public String registration(){
        return "/user/registration";
    }

    @GetMapping("/addUser")
    public String addCompany(){
        return "/user/addUser";
    }
}

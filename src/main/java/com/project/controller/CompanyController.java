package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @GetMapping("/registration")
    public String registration(){
        return "/company/registration";
    }

    @GetMapping("/addCompany")
    public String addCompany(){
        return "/company/addCompany";
    }
}

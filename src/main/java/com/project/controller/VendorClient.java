package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendor_client")
public class VendorClient {


    @GetMapping("/registration")
    public String registration(){
        return "/vendor_client/registration";
    }

    @GetMapping("/addVendor_client")
    public String addCompany(){
        return "/vendor_client/addVendor_client";
    }
}

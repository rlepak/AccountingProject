package com.project.controller;

import com.project.dto.CurrencyExchangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class LoginController {


    @RequestMapping(value = {"/","login"})
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String welcome(Model model){
//        CurrencyExchangeDto currencyExchangeDto = new CurrencyExchangeDto();
//        model.addAttribute("currencyDto", currencyExchangeDto);
        return "index";
    }
}

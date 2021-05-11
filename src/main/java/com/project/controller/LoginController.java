package com.project.controller;

import com.project.dto.CurrencyExchangeDto;
import com.project.entity.User;
import com.project.entity.common.UserPrincipal;
import com.project.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

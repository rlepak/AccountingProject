package com.project.controller;

import com.project.dto.CompanyDto;
import com.project.exception.AccountingProjectException;
import com.project.repository.CompanyRepository;
import com.project.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;
    private CompanyRepository companyRepository;

    public CompanyController(CompanyService companyService, CompanyRepository companyRepository) {
        this.companyService = companyService;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("companies", companyService.listAllCompanies());
        model.addAttribute("states", companyService.listAllCompanies());
        return "/company/registration";
    }


    @GetMapping("/addCompany")
    public String addCompany(CompanyDto company) {
        return "/company/addCompany";
    }

    @PostMapping("/addCompany")
    public String createCompany(CompanyDto companyDto, Model model) throws AccountingProjectException {
        model.addAttribute("companyDto", new CompanyDto());
        companyService.save(companyDto);
        return "redirect:/company/registration";
    }

}

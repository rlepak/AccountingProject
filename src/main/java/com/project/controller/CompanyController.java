package com.project.controller;

import com.project.dto.CompanyDto;
import com.project.exception.AccountingProjectException;
import com.project.repository.CompanyRepository;
import com.project.service.CompanyService;
import com.project.service.StateService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;
    private StateService stateService;

    public CompanyController(CompanyService companyService, StateService stateService) {
        this.companyService = companyService;
        this.stateService = stateService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("companies", companyService.listAllCompanies());
        return "/company/registration";
    }


    @GetMapping("/addCompany")
    public String addCompany(CompanyDto company, Model model) {
        model.addAttribute("states", stateService.listAllStates());
        return "/company/addCompany";
    }

    @PostMapping("/addCompany")
    public String createCompany(CompanyDto companyDto, Model model) throws AccountingProjectException {
        model.addAttribute("companyDto", new CompanyDto());
        companyService.save(companyDto);
        return "redirect:/company/registration";
    }

    @GetMapping("/update/{id}")
    public String editCompany(@PathVariable("id") long id, Model model) throws AccountingProjectException {
        model.addAttribute("companyDto", companyService.findById(id));
        model.addAttribute("states", stateService.listAllStates());
        return "/company/update";
    }

    @PostMapping("/update/{id}")
    public String updateCompany(@PathVariable("id") long id, CompanyDto companyDto) throws AccountingProjectException {
        companyService.update(companyDto);
        return "redirect:/company/registration";
    }

}

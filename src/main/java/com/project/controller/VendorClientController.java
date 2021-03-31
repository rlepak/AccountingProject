package com.project.controller;

import com.project.dto.CompanyDto;
import com.project.dto.VendorClientDto;
import com.project.exception.AccountingProjectException;
import com.project.service.StateService;
import com.project.service.VendorClientService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendor_client")
public class VendorClientController {

    private StateService stateService;
    private VendorClientService vendorClientService;

    public VendorClientController(StateService stateService, VendorClientService vendorClientService) {
        this.stateService = stateService;
        this.vendorClientService = vendorClientService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("vendorsClients", vendorClientService.listAllVendorClient());
        return "/vendor_client/registration";
    }

    @GetMapping("/addVendor_client")
    public String addCompany(VendorClientDto vendorClientDto, Model model){
        model.addAttribute("states", stateService.listAllStates());
        return "/vendor_client/addVendor_client";
    }

    @PostMapping("/addVendor_client")
    public String createVendorClient(VendorClientDto vendorClientDto, Model model) throws AccountingProjectException {
        model.addAttribute("vendorClientDto", new VendorClientDto());
        vendorClientService.save(vendorClientDto);
        return "vendor_client/registration";
    }
}

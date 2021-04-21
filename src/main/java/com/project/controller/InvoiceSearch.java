package com.project.controller;

import com.project.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceSearch {

    private InvoiceService invoiceService;

    public InvoiceSearch(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoiceSearch")
    public String invoiceSearch(Model model){
        model.addAttribute("allInvoices", invoiceService.listAllInvoices());
        return "/invoice/invoiceSearch";
    }
}

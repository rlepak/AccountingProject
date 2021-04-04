package com.project.controller;

import com.project.dto.InvoiceDto;
import com.project.exception.AccountingProjectException;
import com.project.service.InvoiceProductService;
import com.project.service.InvoiceService;
import com.project.service.ProductService;
import com.project.service.VendorClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;
    private ProductService productService;
    private VendorClientService vendorClientService;
    private InvoiceProductService invoiceProductService;

    public InvoiceController(InvoiceService invoiceService, ProductService productService, VendorClientService vendorClientService, InvoiceProductService invoiceProductService) {
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.vendorClientService = vendorClientService;
        this.invoiceProductService = invoiceProductService;
    }

    @GetMapping("/purchaseInvoice")
    public String allPurchaseInvoices(Model model){
        model.addAttribute("purchaseInvoices", invoiceService.listAllInvoices());
        return "/invoice/purchaseInvoice";
    }

    @GetMapping("/addInvoice")
    public String addInvoice(InvoiceDto invoiceDto, Model model) {
        model.addAttribute("vendors", vendorClientService.listAllVendorClient());
        return "/invoice/addInvoice";
    }

    @PostMapping("/addInvoice")
    public String createInvoice(InvoiceDto invoiceDto, Model model) throws AccountingProjectException {
        model.addAttribute("invoiceDto", new InvoiceDto());
        invoiceService.savePurchaseInvoice(invoiceDto);
        return "redirect:/invoice/addItem";
    }

    @GetMapping("/addItem")
    public String addItem(Model model) {
        model.addAttribute("products", productService.listAllProducts());
        return "/invoice/addItem";
    }

}

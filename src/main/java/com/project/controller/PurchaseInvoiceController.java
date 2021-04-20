package com.project.controller;

import com.project.dto.InvoiceDto;
import com.project.dto.InvoiceProductDto;
import com.project.exception.AccountingProjectException;
import com.project.pdfExporter.PurchaseInvoicePDFExporter;
import com.project.repository.InvoiceRepository;
import com.project.service.InvoiceProductService;
import com.project.service.InvoiceService;
import com.project.service.ProductService;
import com.project.service.VendorClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/invoice")
public class PurchaseInvoiceController {

    //to keep invoice number
    String URI;

    private InvoiceService invoiceService;
    private ProductService productService;
    private VendorClientService vendorClientService;
    private InvoiceProductService invoiceProductService;
    private InvoiceRepository invoiceRepository;

    public PurchaseInvoiceController(InvoiceService invoiceService, ProductService productService, VendorClientService vendorClientService, InvoiceProductService invoiceProductService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.vendorClientService = vendorClientService;
        this.invoiceProductService = invoiceProductService;
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/purchaseInvoice")
    public String allPurchaseInvoices(Model model){
        model.addAttribute("purchaseInvoices", invoiceService.listAllPurchaseInvoices());
        return "/invoice/purchaseInvoice";
    }

    @GetMapping("/addPurchaseInvoice")
    public String addPurchaseInvoice(InvoiceDto invoiceDto, Model model) {
        model.addAttribute("vendors", vendorClientService.listAllVendorClient());
        return "invoice/addPurchaseInvoice";
    }

    @PostMapping("/addPurchaseInvoice")
    public String createPurchaseInvoice(InvoiceDto invoiceDto, Model model) throws AccountingProjectException {
        model.addAttribute("invoiceDto", new InvoiceDto());
        invoiceService.savePurchaseInvoice(invoiceDto);
        return "redirect:/invoice/addItemToPurchaseInvoice/" + invoiceDto.getInvoiceNumber();
    }

    @GetMapping("/addItemToPurchaseInvoice/{invoiceNo}")
    public String addItemToPurchaseInvoice(@PathVariable("invoiceNo") String invoiceNo, Model model) throws AccountingProjectException {
        model.addAttribute("invoiceProduct", new InvoiceProductDto());
        model.addAttribute("invoiceProducts", invoiceProductService.findPurchaseSaleInvoiceByInvoiceNumber(invoiceNo));
        model.addAttribute("invoiceDto", invoiceService.findByInvoiceNumber(invoiceNo));
        model.addAttribute("products", productService.listAllProducts());
        URI = invoiceNo;
        return "invoice/addItemToPurchaseInvoice";
    }

    @PostMapping("/addItemToPurchaseInvoice")
    public String createPurchaseItem(InvoiceProductDto invoiceProductDto, Model model) throws AccountingProjectException {
        model.addAttribute("productInvoice", new InvoiceProductDto());
        invoiceProductDto.setInvoice(invoiceRepository.findByInvoiceNumber(URI));
        invoiceProductService.savePurchaseSaleInvoice(invoiceProductDto);
        return "redirect:/invoice/addItemToPurchaseInvoice/" + URI;
    }

    @GetMapping("/deletePurchaseInvoice/{id}")
    public String deletePurchaseInvoice(@PathVariable("id") String id) throws AccountingProjectException {
        invoiceService.deleteByInvoiceNumber(id);
        return "redirect:/invoice/purchaseInvoice";
    }

    @GetMapping("/confirmPurchaseInvoice/{id}")
    public String approvePurchaseInvoice(@PathVariable("id") String id) throws AccountingProjectException {
        invoiceService.approveInvoice(invoiceService.findByInvoiceNumber(id));
        return "redirect:/invoice/purchaseInvoice";
    }

    @GetMapping("/exportPurchaseInvoice/{id}")
    public void exportToPDFPurchaseInvoice(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        response.setContentType("Purchase Invoice");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Purchase Invoice.pdf";
        response.setHeader(headerKey, headerValue);

        List<InvoiceProductDto> listAll = invoiceProductService.findPurchaseSaleInvoiceByInvoiceNumber(id);

        PurchaseInvoicePDFExporter exporter = new PurchaseInvoicePDFExporter(listAll);
        exporter.export(response);

    }

}

package com.project.controller;

import com.project.dto.InvoiceDto;
import com.project.dto.InvoiceProductDto;
import com.project.dto.UserDto;
import com.project.exception.AccountingProjectException;
import com.project.pdfExporter.InvoicePDFExporter;
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
public class InvoiceController {

    //to keep invoice number
    String URI;

    private InvoiceService invoiceService;
    private ProductService productService;
    private VendorClientService vendorClientService;
    private InvoiceProductService invoiceProductService;
    private InvoiceRepository invoiceRepository;

    public InvoiceController(InvoiceService invoiceService, ProductService productService, VendorClientService vendorClientService, InvoiceProductService invoiceProductService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.vendorClientService = vendorClientService;
        this.invoiceProductService = invoiceProductService;
        this.invoiceRepository = invoiceRepository;
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
        return "redirect:/invoice/addItem/" + invoiceDto.getInvoiceNumber();
    }

    @GetMapping("/addItem/{invoiceNo}")
    public String addItem(@PathVariable("invoiceNo") String invoiceNo, Model model) throws AccountingProjectException {
        model.addAttribute("invoiceProduct", new InvoiceProductDto());
        model.addAttribute("invoiceProducts", invoiceProductService.findAllByInvoiceNumber(invoiceNo));
        model.addAttribute("invoiceDto", invoiceService.findByInvoiceNumber(invoiceNo));
        model.addAttribute("products", productService.listAllProducts());
        URI = invoiceNo;
        return "/invoice/addItem";
    }

    @PostMapping("/addItem")
    public String createItem(InvoiceProductDto invoiceProductDto, Model model) throws AccountingProjectException {
        model.addAttribute("productInvoice", new InvoiceProductDto());
        invoiceProductDto.setInvoice(invoiceRepository.findByInvoiceNumber(URI));
        invoiceProductService.save(invoiceProductDto);
        return "redirect:/invoice/addItem/" + URI;
    }

    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable("id") String id) throws AccountingProjectException {
        invoiceService.deleteByInvoiceNumber(id);
        return "redirect:/invoice/purchaseInvoice";
    }

    @GetMapping("/confirm/{id}")
    public String approveInvoice(@PathVariable("id") String id) throws AccountingProjectException {
        invoiceService.approveInvoice(invoiceService.findByInvoiceNumber(id));
        return "redirect:/invoice/purchaseInvoice";
    }

    @GetMapping("/export/{id}")
    public void exportToPDF(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        response.setContentType("Purchase Invoice");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Purchase Invoice.pdf";
        response.setHeader(headerKey, headerValue);

        List<InvoiceProductDto> listAll = invoiceProductService.findAllByInvoiceNumber(id);

        InvoicePDFExporter exporter = new InvoicePDFExporter(listAll);
        exporter.export(response);
        //git hub test
    }

}

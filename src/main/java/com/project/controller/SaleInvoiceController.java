package com.project.controller;

import com.project.dto.InvoiceDto;
import com.project.dto.InvoiceProductDto;
import com.project.exception.AccountingProjectException;
import com.project.pdfExporter.PurchaseInvoicePDFExporter;
import com.project.pdfExporter.SaleInvoicePDFExporter;
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
public class SaleInvoiceController {

    //to keep invoice number
    String URI;

    private InvoiceService invoiceService;
    private ProductService productService;
    private VendorClientService vendorClientService;
    private InvoiceProductService invoiceProductService;
    private InvoiceRepository invoiceRepository;

    public SaleInvoiceController(InvoiceService invoiceService, ProductService productService, VendorClientService vendorClientService, InvoiceProductService invoiceProductService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.productService = productService;
        this.vendorClientService = vendorClientService;
        this.invoiceProductService = invoiceProductService;
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/saleInvoice")
    public String allSaleInvoices(Model model) {
        model.addAttribute("saleInvoices", invoiceService.listAllSaleInvoices());
        return "/invoice/saleInvoice";
    }

    @GetMapping("/addSaleInvoice")
    public String addSaleInvoice(InvoiceDto invoiceDto, Model model) {
        model.addAttribute("vendors", vendorClientService.listAllVendorClient());
        return "invoice/addSaleInvoice";
    }

    @PostMapping("/addSaleInvoice")
    public String createSaleInvoice(InvoiceDto invoiceDto, Model model) throws AccountingProjectException {
        model.addAttribute("invoiceDto", new InvoiceDto());
        invoiceService.saveSaleInvoice(invoiceDto);
        return "redirect:/invoice/addItemToSaleInvoice/" + invoiceDto.getInvoiceNumber();
    }

    @GetMapping("/addItemToSaleInvoice/{invoiceNo}")
    public String addItemToSaleInvoice(@PathVariable("invoiceNo") String invoiceNo, Model model) throws AccountingProjectException {
        model.addAttribute("invoiceProduct", new InvoiceProductDto());
        model.addAttribute("invoiceProducts", invoiceProductService.findPurchaseSaleInvoiceByInvoiceNumber(invoiceNo));
        model.addAttribute("invoiceDto", invoiceService.findByInvoiceNumber(invoiceNo));
        model.addAttribute("products", productService.listAllProducts());
        URI = invoiceNo;
        return "invoice/addItemToSaleInvoice";
    }

    @PostMapping("/addItemToSaleInvoice")
    public String createSaleItem(InvoiceProductDto invoiceProductDto, Model model) throws AccountingProjectException {
        model.addAttribute("productInvoice", new InvoiceProductDto());
        invoiceProductDto.setInvoice(invoiceRepository.findByInvoiceNumber(URI));
        invoiceProductService.savePurchaseSaleInvoice(invoiceProductDto);
        return "redirect:/invoice/addItemToSaleInvoice/" + URI;
    }

    @GetMapping("/deleteSaleInvoice/{id}")
    public String deleteSaleInvoice(@PathVariable("id") String id) throws AccountingProjectException {
        invoiceService.deleteByInvoiceNumber(id);
        return "redirect:/invoice/saleInvoice";
    }

    @GetMapping("/confirmSaleInvoice/{id}")
    public String approveSaleInvoice(@PathVariable("id") String id) throws AccountingProjectException {
        invoiceService.approveInvoice(invoiceService.findByInvoiceNumber(id));
        return "redirect:/invoice/saleInvoice";
    }

    @GetMapping("/exportSaleInvoice/{id}")
    public void exportToPDFSaleInvoice(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        response.setContentType("Sale Invoice");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Sale Invoice.pdf";
        response.setHeader(headerKey, headerValue);

        List<InvoiceProductDto> listAll = invoiceProductService.findPurchaseSaleInvoiceByInvoiceNumber(id);

        SaleInvoicePDFExporter exporter = new SaleInvoicePDFExporter(listAll);
        exporter.export(response);

    }
}

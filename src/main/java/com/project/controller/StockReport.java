package com.project.controller;

import com.project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reporting/stockReport")
public class StockReport {

    private ProductService productService;

    public StockReport(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String stockReport(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "/reporting/stockReport";
    }
}

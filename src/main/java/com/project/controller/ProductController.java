package com.project.controller;

import com.project.dto.ProductDto;
import com.project.exception.AccountingProjectException;
import com.project.service.CategoryService;
import com.project.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String allProducts(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "/product/products";
    }

    @GetMapping("/addProduct")
    public String addProduct(ProductDto productDto, Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String createProduct(ProductDto productDto, Model model) throws AccountingProjectException {
        model.addAttribute("productDto", new ProductDto());
        productService.save(productDto);
        return "redirect:/product";
    }

    //TODO missed Update
}

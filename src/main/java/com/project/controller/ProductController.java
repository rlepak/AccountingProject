package com.project.controller;

import com.project.dto.ProductDto;
import com.project.exception.AccountingProjectException;
import com.project.service.CategoryService;
import com.project.service.ProductService;
import com.project.service.UserService;
import org.springframework.security.core.Authentication;
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
    private UserService userService;

    public ProductController(ProductService productService, CategoryService categoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping()
    public String allProducts(Model model, Authentication authentication) throws AccountingProjectException {
        model.addAttribute("products", productService.listAllProductsByCompanyId(userService.findByEmail(authentication.getName()).getCompany().getId()));
        return "/product/products";
    }

    @GetMapping("/addProduct")
    public String addProduct(ProductDto productDto, Model model) {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "/product/addProduct";
    }

    @PostMapping("/addProduct")
    public String createProduct(ProductDto productDto, Model model, Authentication authentication) throws AccountingProjectException {
        model.addAttribute("productDto", new ProductDto());
        productService.save(productDto, authentication);
        return "redirect:/product";
    }

    //TODO missed Update
}

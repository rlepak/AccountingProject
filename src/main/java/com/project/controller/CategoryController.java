package com.project.controller;

import com.project.dto.CategoryDto;
import com.project.dto.ProductDto;
import com.project.dto.UserDto;
import com.project.exception.AccountingProjectException;
import com.project.service.CategoryService;
import com.project.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    private UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping()
    public String allCategories(Model model, Authentication authentication) throws AccountingProjectException {
        model.addAttribute("categories", categoryService.listAllCategoriesByCompanyId(userService.findByEmail(authentication.getName()).getCompany().getId()));
        return "/category/categories";
    }

    @GetMapping("/addCategory")
    public String addCategory(CategoryDto categoryDto, Model model) {
        return "/category/addCategory";
    }

    @PostMapping("/addCategory")
    public String createCategory(CategoryDto categoryDto, Model model, Authentication authentication) throws AccountingProjectException {
        model.addAttribute("categoryDto", new CategoryDto());
        categoryService.save(categoryDto, authentication);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") long id, CategoryDto categoryDto) throws AccountingProjectException {
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }

    @GetMapping("/update/{id}")
    public String editUser(@PathVariable("id") long id, Model model) throws AccountingProjectException {
        model.addAttribute("categories", categoryService.listAllCategories());
        return "/user/update";
    }

//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") long id, UserDto userDto) throws AccountingProjectException {
//        userService.update(userDto);
//        return "redirect:/user/registration";
//    }
}

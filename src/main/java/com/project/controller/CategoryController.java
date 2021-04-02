package com.project.controller;

import com.project.dto.CategoryDto;
import com.project.dto.ProductDto;
import com.project.dto.UserDto;
import com.project.exception.AccountingProjectException;
import com.project.service.CategoryService;
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

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String allCategories(Model model){
        model.addAttribute("categories", categoryService.listAllCategories());
        return "/category/categories";
    }

    @GetMapping("/addCategory")
    public String addCategory(CategoryDto categoryDto, Model model) {
        return "/category/addCategory";
    }

    @PostMapping("/addCategory")
    public String createCategory(CategoryDto categoryDto, Model model) throws AccountingProjectException {
        model.addAttribute("categoryDto", new CategoryDto());
        categoryService.save(categoryDto);
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

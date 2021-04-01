package com.project.controller;

import com.project.dto.CompanyDto;
import com.project.dto.UserDto;
import com.project.entity.Role;
import com.project.exception.AccountingProjectException;
import com.project.service.CompanyService;
import com.project.service.RoleService;
import com.project.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private CompanyService companyService;

    public UserController(UserService userService, RoleService roleService, CompanyService companyService) {
        this.userService = userService;
        this.roleService = roleService;
        this.companyService = companyService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "/user/registration";
    }

    @GetMapping("/addUser")
    public String addUser(UserDto userDto, Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("companies", companyService.listAllCompanies());
        return "/user/addUser";
    }

    @PostMapping("/addUser")
    public String createUser(UserDto userDto, Model model) throws AccountingProjectException {
        userService.save(userDto);
        return "redirect:/user/registration";
    }

    @GetMapping("/update/{id}")
    public String editUser(@PathVariable("id") long id, Model model) throws AccountingProjectException {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAllRoles());
        model.addAttribute("companies", companyService.listAllCompanies());
        return "/user/update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, UserDto userDto) throws AccountingProjectException {
        userService.update(userDto);
        return "redirect:/user/registration";
    }

    @GetMapping("/delete/{email}")
    public String deleteUser(@PathVariable("email") String email, UserDto userDto) throws AccountingProjectException {
        userService.deleteUser(email);
        return "redirect:/user/registration";
    }



    }

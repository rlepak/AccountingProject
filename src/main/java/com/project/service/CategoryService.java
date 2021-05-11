package com.project.service;

import com.project.dto.CategoryDto;
import com.project.dto.CompanyDto;
import com.project.dto.ProductDto;
import com.project.entity.Category;
import com.project.entity.common.UserPrincipal;
import com.project.exception.AccountingProjectException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> listAllCategories();
    List<CategoryDto> listAllCategoriesByCompanyId(Long id);
    CategoryDto findById(Long id) throws AccountingProjectException;
    CategoryDto save(CategoryDto categoryDto, Authentication authentication) throws AccountingProjectException;
    CategoryDto update (CategoryDto categoryDto) throws AccountingProjectException;
    void deleteCategory(long id) throws AccountingProjectException;


}

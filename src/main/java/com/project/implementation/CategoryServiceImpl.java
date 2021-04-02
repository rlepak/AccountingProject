package com.project.implementation;

import com.project.dto.CategoryDto;
import com.project.dto.CompanyDto;
import com.project.dto.ProductDto;
import com.project.entity.Category;
import com.project.entity.Company;
import com.project.entity.Product;
import com.project.entity.User;
import com.project.exception.AccountingProjectException;
import com.project.repository.CategoryRepository;
import com.project.service.CategoryService;
import com.project.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private MapperUtil mapperUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, MapperUtil mapperUtil) {
        this.categoryRepository = categoryRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CategoryDto> listAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream().map(category -> mapperUtil.convert(category, new CategoryDto())).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) throws AccountingProjectException {

        Category category = categoryRepository.findById(id).orElse(null);
        if (category==null){
            throw new AccountingProjectException("Category with " + id + " not exist");
        }
        return mapperUtil.convert(category, new CategoryDto());
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) throws AccountingProjectException {
        Category category = categoryRepository.findById(categoryDto.getId()).orElse(null);
        if(category!=null){
            throw  new AccountingProjectException("This category exist");
        }
        Category categoryObject = mapperUtil.convert(categoryDto, new Category());
        Category savedCategory = categoryRepository.save(categoryObject);
        return mapperUtil.convert(savedCategory, new CategoryDto());
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) throws AccountingProjectException {
        Category category = categoryRepository.findById(categoryDto.getId()).orElse(null);
        Category updatedCategory = mapperUtil.convert(categoryDto, new Category());
        updatedCategory.setId(category.getId());
        categoryRepository.save(updatedCategory);
        return findById(categoryDto.getId());
    }

    @Override
    public void deleteCategory(long id) throws AccountingProjectException {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new AccountingProjectException("Category with " + id + " not exist");
        }
        category.setIsDeleted(true);
        categoryRepository.save(category);
    }
}

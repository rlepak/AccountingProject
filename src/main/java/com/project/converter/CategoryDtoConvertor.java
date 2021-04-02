package com.project.converter;

import com.project.dto.CategoryDto;
import com.project.dto.RoleDto;
import com.project.service.CategoryService;
import com.project.service.RoleService;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CategoryDtoConvertor implements Converter<String, CategoryDto>{

    private CategoryService categoryService;

    public CategoryDtoConvertor(@Lazy CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @SneakyThrows
    @Override
    public CategoryDto convert(String source) {
        Long id = Long.parseLong(source);
        return categoryService.findById(id);
    }
}


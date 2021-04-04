package com.project.converter;

import com.project.dto.InvoiceProductDto;
import com.project.dto.ProductDto;
import com.project.dto.RoleDto;
import com.project.service.InvoiceService;
import com.project.service.ProductService;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ProductConverter implements Converter<String, ProductDto> {

    private ProductService productService;

    public ProductConverter(@Lazy ProductService productService) {
        this.productService = productService;
    }

    @SneakyThrows
    @Override
    public ProductDto convert(String source) {
        Long id = Long.parseLong(source);
        return productService.findById(id) ;
    }
}

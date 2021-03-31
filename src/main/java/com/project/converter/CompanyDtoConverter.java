package com.project.converter;

import com.project.dto.CompanyDto;
import com.project.service.CompanyService;
import com.project.service.StateService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CompanyDtoConverter implements Converter<String, CompanyDto> {


    private CompanyService companyService;

    public CompanyDtoConverter(@Lazy CompanyService companyService) {
        this.companyService = companyService;
    }

    @SneakyThrows
    @Override
    public CompanyDto convert(String code) {
        return companyService.findByEmail(code);
    }
}

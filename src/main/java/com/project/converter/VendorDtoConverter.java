package com.project.converter;

import com.project.dto.VendorClientDto;
import com.project.service.VendorClientService;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class VendorDtoConverter implements Converter<String, VendorClientDto> {

    private VendorClientService vendorClientService;

    public VendorDtoConverter(@Lazy VendorClientService vendorClientService) {
        this.vendorClientService = vendorClientService;
    }


    @SneakyThrows
    @Override
    public VendorClientDto convert(String source) {
        Long id = Long.parseLong(source);
        return vendorClientService.findById(id) ;
    }
}

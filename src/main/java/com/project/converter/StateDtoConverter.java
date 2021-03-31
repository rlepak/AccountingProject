package com.project.converter;

import com.project.dto.StateDto;
import com.project.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class StateDtoConverter implements Converter<String, StateDto> {

    private StateService stateService;

    public StateDtoConverter(@Lazy StateService stateService) {
        this.stateService = stateService;
    }


    @Override
    public StateDto convert(String code) {
        return stateService.findByCode(code);
    }
}

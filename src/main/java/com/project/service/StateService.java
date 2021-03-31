package com.project.service;

import com.project.dto.StateDto;

import java.util.List;

public interface StateService {

    List<StateDto> listAllStates();
    StateDto findByCode(String code);
}

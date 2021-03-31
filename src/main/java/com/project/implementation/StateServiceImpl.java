package com.project.implementation;

import com.project.dto.CompanyDto;
import com.project.dto.StateDto;
import com.project.entity.State;
import com.project.repository.StateRepository;
import com.project.service.StateService;
import com.project.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService {


    private StateRepository stateRepository;
    private MapperUtil mapperUtil;

    public StateServiceImpl(StateRepository stateRepository, MapperUtil mapperUtil) {
        this.stateRepository = stateRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<StateDto> listAllStates() {
        List<State> stateList = stateRepository.findAll();
        return stateList.stream().map(obj -> mapperUtil.convert(obj, new StateDto())).collect(Collectors.toList());
    }

    @Override
    public StateDto findByCode(String code) {
        State state = stateRepository.findByCode(code);
        return mapperUtil.convert(state, new StateDto());
    }


}

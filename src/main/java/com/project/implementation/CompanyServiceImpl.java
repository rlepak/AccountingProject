package com.project.implementation;

import com.project.dto.CompanyDto;
import com.project.entity.Company;
import com.project.exception.AccountingProjectException;
import com.project.repository.CompanyRepository;
import com.project.service.CompanyService;
import com.project.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private MapperUtil mapperUtil;

    public CompanyServiceImpl(CompanyRepository companyRepository, MapperUtil mapperUtil) {
        this.companyRepository = companyRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<CompanyDto> listAllCompanies() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(obj -> mapperUtil.convert(obj, new CompanyDto())).collect(Collectors.toList());
    }

    @Override
    public CompanyDto save(CompanyDto companyDto) throws AccountingProjectException {
        Company foundCompany = companyRepository.findByEmail(companyDto.getEmail());
        if(foundCompany!=null){
            throw  new AccountingProjectException("This company exist");
        }

        companyDto.setEnabled(true);
        companyDto.setSuccessfulMessage(companyDto.getTitle());
        Company companyObject = mapperUtil.convert(companyDto, new Company());
        Company savedCompany = companyRepository.save(companyObject);
        return mapperUtil.convert(savedCompany, new CompanyDto());
    }
}

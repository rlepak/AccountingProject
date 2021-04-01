package com.project.implementation;

import com.project.dto.CompanyDto;
import com.project.entity.Company;
import com.project.exception.AccountingProjectException;
import com.project.repository.CompanyRepository;
import com.project.service.CompanyService;
import com.project.util.MapperUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.OrderBy;
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
        List<Company> companyList = companyRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

//        return companyList.stream().map(company -> {CompanyDto companyDto =  mapperUtil.convert(company, new CompanyDto()); companyDto.setRepresentative("Admin"); return companyDto;}).collect(Collectors.toList());
        return companyList.stream().map(company -> mapperUtil.convert(company, new CompanyDto())).collect(Collectors.toList());
    }

    @Override
    public CompanyDto save(CompanyDto companyDto) throws AccountingProjectException {
        Company foundCompany = companyRepository.findByEmail(companyDto.getEmail());
        if(foundCompany!=null){
            throw  new AccountingProjectException("This company exist");
        }

        Company companyObject = mapperUtil.convert(companyDto, new Company());
        Company savedCompany = companyRepository.save(companyObject);
        return mapperUtil.convert(savedCompany, new CompanyDto());
    }

    @Override
    public CompanyDto findByEmail(String email) throws AccountingProjectException {
        Company company = companyRepository.findByEmail(email);
        if (company==null){
            throw new AccountingProjectException("Company with " + email + " not exist");
        }
        return mapperUtil.convert(company, new CompanyDto());
    }

    @Override
    public CompanyDto findById(Long id) throws AccountingProjectException {
        Company company = companyRepository.findById(id).orElseThrow();
        if (company==null){
            throw new AccountingProjectException("Company with " + id + " not exist");
        }
        return mapperUtil.convert(company, new CompanyDto());
    }

    @Override
    public CompanyDto update(CompanyDto companyDto) throws AccountingProjectException {
        Company company = companyRepository.findByEmail(companyDto.getEmail());

        Company updatedCompany = mapperUtil.convert(companyDto, new Company());

        updatedCompany.setId(company.getId());
        updatedCompany.setState(company.getState());

        companyRepository.save(updatedCompany);
        return findByEmail(companyDto.getEmail());
    }


}

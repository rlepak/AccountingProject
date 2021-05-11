package com.project.service;

import com.project.dto.CompanyDto;
import com.project.entity.User;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> listAllCompanies();
    CompanyDto save(CompanyDto companyDto) throws AccountingProjectException;
    CompanyDto findByEmail(String email) throws AccountingProjectException;
    CompanyDto findById(Long id) throws AccountingProjectException;
    CompanyDto update (CompanyDto companyDto) throws AccountingProjectException;
    CompanyDto findCompanyByUser(String username);

}

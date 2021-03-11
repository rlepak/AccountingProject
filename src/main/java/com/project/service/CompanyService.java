package com.project.service;

import com.project.dto.CompanyDto;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> listAllCompanies();
    CompanyDto save(CompanyDto companyDto) throws AccountingProjectException;
}

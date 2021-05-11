package com.project.service;

import com.project.dto.CategoryDto;
import com.project.dto.CompanyDto;
import com.project.dto.VendorClientDto;
import com.project.exception.AccountingProjectException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface VendorClientService {

    List<VendorClientDto> listAllVendorClient();
    List<VendorClientDto> listAllVendorClientByCompanyId(Long id);
    VendorClientDto save (VendorClientDto vendorClientDto, Authentication authentication) throws AccountingProjectException;
    VendorClientDto findByEmail(String email) throws AccountingProjectException;
    VendorClientDto findById(Long id) throws AccountingProjectException;
    VendorClientDto update (VendorClientDto vendorClientDto) throws AccountingProjectException;
    void deleteVendorClient(String email) throws AccountingProjectException;
}

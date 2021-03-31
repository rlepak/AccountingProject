package com.project.service;

import com.project.dto.CompanyDto;
import com.project.dto.VendorClientDto;
import com.project.exception.AccountingProjectException;

import java.util.List;

public interface VendorClientService {

    List<VendorClientDto> listAllVendorClient();
    VendorClientDto save (VendorClientDto vendorClientDto) throws AccountingProjectException;
    VendorClientDto findByEmail(String email) throws AccountingProjectException;
    VendorClientDto findById(Long id) throws AccountingProjectException;
    VendorClientDto update (VendorClientDto vendorClientDto) throws AccountingProjectException;

}

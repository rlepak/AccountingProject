package com.project.implementation;

import com.project.dto.CompanyDto;
import com.project.dto.VendorClientDto;
import com.project.entity.Company;
import com.project.entity.VendorClient;
import com.project.exception.AccountingProjectException;
import com.project.repository.VendorClientRepository;
import com.project.service.VendorClientService;
import com.project.util.MapperUtil;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorClientImpl implements VendorClientService {

    private VendorClientRepository vendorClientRepository;
    private MapperUtil mapperUtil;

    public VendorClientImpl(VendorClientRepository vendorClientRepository, MapperUtil mapperUtil) {
        this.vendorClientRepository = vendorClientRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<VendorClientDto> listAllVendorClient() {
        List <VendorClient> vendorClients = vendorClientRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return vendorClients.stream().map(vendorClient -> mapperUtil.convert(vendorClient, new VendorClientDto())).collect(Collectors.toList());
    }

    @Override
    public VendorClientDto save(VendorClientDto vendorClientDto) throws AccountingProjectException {
      VendorClient foundVendorClient = vendorClientRepository.findByEmail(vendorClientDto.getEmail());

      if (foundVendorClient!=null){
          throw  new AccountingProjectException("This Vendor/Client exist");
      }

      VendorClient vendorClientObject = mapperUtil.convert(vendorClientDto, new VendorClient());
      VendorClient savedVendorClient = vendorClientRepository.save(vendorClientObject);
      return mapperUtil.convert(savedVendorClient, new VendorClientDto());
    }

    @Override
    public VendorClientDto findByEmail(String email) throws AccountingProjectException {
        VendorClient vendorClient = vendorClientRepository.findByEmail(email);
        if (vendorClient==null){
            throw new AccountingProjectException("Vendor/Client with " + email + " not exist");
        }
        return mapperUtil.convert(vendorClient, new VendorClientDto());
    }

    @Override
    public VendorClientDto findById(Long id) throws AccountingProjectException {
        VendorClient vendorClient = vendorClientRepository.findById(id).orElseThrow();
        if (vendorClient==null){
            throw new AccountingProjectException("Vendor/Client with " + id + " not exist");
        }
        return mapperUtil.convert(vendorClient, new VendorClientDto());
    }

    @Override
    public VendorClientDto update(VendorClientDto vendorClientDto) throws AccountingProjectException {
        VendorClient vendorClient = vendorClientRepository.findByEmail(vendorClientDto.getEmail());

        VendorClient updatedVendorClient = mapperUtil.convert(vendorClientDto, new VendorClient());

        updatedVendorClient.setId(vendorClient.getId());
        vendorClientRepository.save(updatedVendorClient);
        return findByEmail(vendorClientDto.getEmail());
    }
}

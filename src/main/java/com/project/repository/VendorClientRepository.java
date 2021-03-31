package com.project.repository;

import com.project.dto.VendorClientDto;
import com.project.entity.Company;
import com.project.entity.VendorClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorClientRepository extends JpaRepository<VendorClient, Long> {

    VendorClient findByEmail(String email);
}

package com.project.repository;

import com.project.entity.Company;
import com.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByEmail(String email);
//    @Query(value = "select * from Company c where c.representative")

    @Query(value = "select * from company\n" +
            "join users u on company.id = u.company_id\n" +
            "where u.email = ?1", nativeQuery = true)
    Company findCompanyByUserEmail(String email);

    Company findAllByUsers(User user);


}

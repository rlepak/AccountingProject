package com.project.repository;

import com.project.entity.Company;
import com.project.entity.Role;
import com.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        @Query(value = "select * from company c\n" +
                "join users u on c.id = u.company_id\n" +
                "where c.title = ?1 and u.role_id = 1", nativeQuery = true)
        List<User> listOfAllUsersWithRoleAdmin(String companyName);


        User findByEmail(String email);



}

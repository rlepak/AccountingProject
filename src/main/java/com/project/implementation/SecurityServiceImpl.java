package com.project.implementation;

import com.project.entity.User;
import com.project.entity.common.UserPrincipal;
import com.project.exception.AccountingProjectException;
import com.project.repository.UserRepository;
import com.project.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);

        if (user==null){
            throw new UsernameNotFoundException("User does not exist");
        }
        return new UserPrincipal(user);
    }
}

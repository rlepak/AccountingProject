package com.project.implementation;

import com.project.dto.UserDto;
import com.project.entity.User;
import com.project.exception.AccountingProjectException;
import com.project.repository.UserRepository;
import com.project.service.UserService;
import com.project.util.MapperUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private MapperUtil mapperUtil;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> listAllUsersByCompanyId(Long id) {
        List<User> allUsers = userRepository.findAllByCompanyId(id);
        return allUsers.stream().map(obj -> mapperUtil.convert(obj, new UserDto())).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(obj -> mapperUtil.convert(obj, new UserDto())).collect(Collectors.toList());
    }

    @Override
    public UserDto save(UserDto userDto) throws AccountingProjectException {

        User foundUser = userRepository.findByEmail(userDto.getEmail());
        if (foundUser != null) {
            throw new AccountingProjectException("This user exist");
        }

//        TODO verify if company has admin
//        List<User> listOfAdmins = userRepository.listOfAllUsersWithRoleAdmin(userDto.getCompany().getTitle());
//
//        if (listOfAdmins.size()>0){
//            throw new AccountingProjectException("This Company already have Admin");
//        }

        User userObject = mapperUtil.convert(userDto, new User());
        //encoding password
        userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
        User savedUser = userRepository.save(userObject);
        return mapperUtil.convert(savedUser, new UserDto());
    }

    @Override
    public UserDto findById(Long id) throws AccountingProjectException {
        User user = userRepository.findById(id).orElseThrow();
        if (user == null) {
            throw new AccountingProjectException("User with " + id + " not exist");
        }
        return mapperUtil.convert(user, new UserDto());
    }

    @Override
    public UserDto update(UserDto userDto) throws AccountingProjectException {
        User user = userRepository.findByEmail(userDto.getEmail());

        User updatedUser = mapperUtil.convert(userDto, new User());

        updatedUser.setId(user.getId());

        userRepository.save(updatedUser);
        return findByEmail(userDto.getEmail());
    }

    @Override
    public UserDto findByEmail(String email) throws AccountingProjectException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AccountingProjectException("User with " + email + " not exist");
        }
        return mapperUtil.convert(user, new UserDto());
    }

    @Override
    public void deleteUser(String email) throws AccountingProjectException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new AccountingProjectException("User with " + email + " not exist");
        }
        user.setIsDeleted(true);
        userRepository.save(user);
    }


}

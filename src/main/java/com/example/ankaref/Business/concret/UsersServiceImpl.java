package com.example.ankaref.Business.concret;

import com.example.ankaref.Business.Abstracts.RolService;
import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreateRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.TokenResponse;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetAllUsersResponse;

import com.example.ankaref.DTO.Response.User.GetByIdUsersResponse;
import com.example.ankaref.DataAccess.RoleRepository;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Role;
import com.example.ankaref.Entities.Users;

import com.example.ankaref.Security.config.TokenManager;
import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


import java.util.stream.Collectors;

@Slf4j
@Service//Kullanici Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    // private final ModelMapperService modelMapperService;
    private RoleRepository roleRepository;
    private RolService service;
    private final TokenManager tokenManager;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper, RolService service, TokenManager tokenManager) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.service = service;
        this.roleRepository = roleRepository;
        this.tokenManager = tokenManager;
    }


    public List<GetAllUsersResponse> getAll() {//Bütün userları çekme
        List<Users> Users = userRepository.findAll();
        List<GetAllUsersResponse> UsersResponse = Users.stream().map(user -> mapper.map(user, GetAllUsersResponse.class)).collect(Collectors.toList());


        return UsersResponse;
    }

    @Override
    public GetByIdUsersResponse getId(Long id) {
        Users users = userRepository.findById(id).orElseThrow(() -> new EntityExistsException("User not found"));
        // getByIdUsersResponse response = this.modelMapperService.forResponse().map(users, getByIdUsersResponse.class);
        var dto = mapper.map(users, GetByIdUsersResponse.class);

        return dto;
    }


    @Override//buraya akılacak
    public void creatRequest(CreateRequest createRequest) {
        Users users = mapper.map(createRequest, Users.class);
        users.setPassword(passwordEncoder.encode(createRequest.getPassword()));
        Set<Role> userRoles = new HashSet<>();

        Role existingRole = roleRepository.findByRoleName("USER");
        userRoles.add(existingRole);
        users.setRoles(userRoles);
        this.userRepository.save(users);
        //     for (Role role : createRequest.getRoles()) {
//         Role existingRole = roleRepository.findByRoleName("USER");
//           if (existingRole != null) {
//                userRoles.add(existingRole);
//           }
//        }
    }


    @Override
    public void updateRequest(UpdateRequest updateRequest) {//kontrolü yapıldı

        Users users = mapper.map(updateRequest, Users.class);


        users.setPassword(passwordEncoder.encode(updateRequest.getPassword()));

        userRepository.save(users);


    }

    @Override
    public void deleteUser(Long id) {//iş konturolü yapıldı.
        if (userRepository.findById(id).isEmpty()) {
            throw new ArithmeticException("Do not find the user");
        } else {
            this.userRepository.deleteById(id);
            System.out.println("Delete is successful");
        }
    }

    @Override
    public TokenResponse login(Login login) {//iş kontrolü çalışıyor
        var user = userRepository.findByEmail(login.getEmail()).orElseThrow();
        var token = tokenManager.generateToken(user);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        log.info("Generate Token: " + login.getEmail());
        return TokenResponse.builder().token(token).username(login.getEmail()).name(user.getName()).surname(user.getLastName()).build();
    }
}

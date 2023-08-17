package com.example.ankaref.Business.concret;

import com.example.ankaref.Business.Abstracts.RolService;
import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreateRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetAllUsersResponse;

import com.example.ankaref.DTO.Response.User.GetByIdUsersResponse;
import com.example.ankaref.DataAccess.RoleRepository;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Role;
import com.example.ankaref.Entities.Users;

import jakarta.persistence.EntityExistsException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service//Kullanici Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    // private final ModelMapperService modelMapperService;
    private RoleRepository roleRepository;
    private RolService service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper mapper,RolService service) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.service=service;
        this.roleRepository=roleRepository;
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
        // Users users = this.modelMapperService.forRequest().map(creatRequest, Users.class);
        Users users = mapper.map(createRequest, Users.class);
        Set<Role> roles = null;
        String getEmail = users.getEmail();
        String greenmail = String.valueOf(userRepository.findByEmail(getEmail));
        if (Objects.equals(users.getEmail(), greenmail)) {

            throw new ArithmeticException("Has been created users");

        } else {


            Set<Role> userRoles = new HashSet<>();



            for (Role role : createRequest.getRoles()) {
                Role existingRole = roleRepository.findByRoleName(role.getRoleName());
                if (existingRole != null) {
                    userRoles.add(existingRole);
                }
            }
            users.setRoles(userRoles);
            this.userRepository.save(users);


        }

    }



    @Override
    public void updateRequest(UpdateRequest updateRequest) {
        // Users users = this.modelMapperService.forRequest().map(updateRequest, Users.class);

        if (userRepository.findByEmail(updateRequest.getEmail()).isPresent()) {
            Users users = mapper.map(updateRequest, Users.class);
            System.out.println("Update successfully");
        } else {
            throw new ArithmeticException("User has been not  found in repository");
        }

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
    public String login(Login login) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        org.springframework.security.core.Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        //String jwtToken=jwtTokenProvider.generateJwtToken(auth);

        return "Bearer";
    }
}

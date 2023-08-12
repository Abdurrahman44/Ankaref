package com.example.ankaref.Business.concret;

import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetAllUsersResponse;

import com.example.ankaref.DTO.Response.User.GetByIdUsersResponse;
import com.example.ankaref.DataAccess.RoleRepository;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Role;
import com.example.ankaref.Entities.Users;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service//Kullanici Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    // private final ModelMapperService modelMapperService;
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;


    //    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    public List<GetAllUsersResponse> getAll() {//Bütün userları çekme
        List<Users> Users = userRepository.findAll();
        List<GetAllUsersResponse> UsersResponse = Users.stream().map(user -> mapper.map(user, GetAllUsersResponse.class)).collect(Collectors.toList());


        return UsersResponse;
    }

    @Override
    public GetByIdUsersResponse getId(Long id) {
        Users users = userRepository.findById(id).orElseThrow();
        // getByIdUsersResponse response = this.modelMapperService.forResponse().map(users, getByIdUsersResponse.class);
        var dto = mapper.map(users, GetByIdUsersResponse.class);

        return dto;
    }


    @Override//buraya akılacak
    public void creatRequest(CreatRequest creatRequest) {
        // Users users = this.modelMapperService.forRequest().map(creatRequest, Users.class);
        Users users = mapper.map(creatRequest, Users.class);
        Set<Role> roles = null;

        for (Role r : creatRequest.getRoles()) {
            var rol = roleRepository.findById(r.getRoleId()).get();
            roles.add(rol);
        }
        users.setRoles(roles);
        this.userRepository.save(users);
    }

    @Override
    public void updateRequest(UpdateRequest updateRequest) {
        // Users users = this.modelMapperService.forRequest().map(updateRequest, Users.class);
        try {

            if (userRepository.findByEmail(updateRequest.getEmail()) != null) {
                Users users = mapper.map(updateRequest, Users.class);
                System.out.println("İşlem başarıyla gerçekleşti");
            } else {
                throw new ArithmeticException("Böyle bir kişi veri tabanına kayıtlı değil");
            }

        } catch (
                Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);

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

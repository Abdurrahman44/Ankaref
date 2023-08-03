package com.example.ankaref.Business.concret;

import com.example.ankaref.Business.Abstracts.UsersService;
import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetAllKullanicilarResponse;
import com.example.ankaref.DTO.Response.User.getByIdUsersResponse;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Users;
import com.example.ankaref.Mapper.ModelMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service//Kullanici Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;

    @Autowired
    private AuthenticationManager authenticationManager;


//    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UserRepository userRepository, ModelMapperService modelMapperService) {
        this.userRepository = userRepository;
        this.modelMapperService = modelMapperService;
    }


    public List<GetAllKullanicilarResponse> getAll() {
        List<Users> Users = userRepository.findAll();
        List<GetAllKullanicilarResponse> UsersResponse = Users.stream()
                .map(user -> this.modelMapperService
                        .forResponse().map(user, GetAllKullanicilarResponse.class)).collect(Collectors.toList());
        return UsersResponse;
    }

    @Override
    public getByIdUsersResponse getId(Long id) {
        Users users = userRepository.findById(id).orElseThrow();

        getByIdUsersResponse response = this.modelMapperService.forResponse().map(users, getByIdUsersResponse.class);
        return response;
    }


    @Override
    public void creatRequest(CreatRequest creatRequest) {
        Users users = this.modelMapperService.forRequest().map(creatRequest, Users.class);
        this.userRepository.save(users);
    }

    @Override
    public void updateRequest(UpdateRequest updateRequest) {
        Users users = this.modelMapperService.forRequest().map(updateRequest, Users.class);
    }


    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);

    }

    @Override
    public String login(Login login) {
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        org.springframework.security.core.Authentication  auth =  authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        //String jwtToken=jwtTokenProvider.generateJwtToken(auth);

        return "Bearer";
    }
}

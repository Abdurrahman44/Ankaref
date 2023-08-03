package com.example.ankaref.Business.concret;

import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;

import com.example.ankaref.DTO.Response.User.GetAllKullanicilarResponse;
import com.example.ankaref.DTO.Response.User.GetByIdKullanicilarResponse;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Users;
import com.example.ankaref.Mapper.ModelMapperService;

import com.example.ankaref.Security.JwtTokenProvider;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service//Kullanici Service
@Data
public class UsersServiceImpl implements com.example.ankaref.Business.Abstracts.UsersService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired

    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GetAllKullanicilarResponse> getAll() {
        List<Users> Users = userRepository.findAll();
        List<GetAllKullanicilarResponse> UsersResponse = Users.stream()
                .map(user -> this.modelMapperService
                        .forResponse().map(user, GetAllKullanicilarResponse.class)).collect(Collectors.toList());
        return UsersResponse;
    }

    @Override
    public GetByIdKullanicilarResponse getId(Long id) {
        Users users = userRepository.findById(id).orElseThrow();

        GetByIdKullanicilarResponse response = this.modelMapperService.forResponse().map(users, GetByIdKullanicilarResponse.class);
        return response;
    }

    public String login (@RequestBody CreatRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        org.springframework.security.core.Authentication  auth =  authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken=jwtTokenProvider.generateJwtToken(auth);

        return "Bearer"+jwtToken;
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
        String jwtToken=jwtTokenProvider.generateJwtToken(auth);

        return "Bearer"+jwtToken;
    }
}

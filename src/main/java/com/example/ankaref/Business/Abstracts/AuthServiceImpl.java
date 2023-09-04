package com.example.ankaref.Business.Abstracts;


import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.TokenResponse;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Role;
import com.example.ankaref.Entities.Users;
import com.example.ankaref.Security.config.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthServiceImpl {
    private final PasswordEncoder encoder;
    private final TokenManager tokenManager;
    private final AuthenticationManager authManager;
    private final UserRepository repository;

    public Users createuser(Users dto) {

        Role role = new Role();
        //role.setName("ADMIN");

        Users user = new Users();
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRoles(Set.of(role));
        var newUser = repository.save(user);

        log.info("Create User: " + newUser.getId());
        return newUser;
    }

   /* public TokenResponse authenticate(Login dto) {
        var user = repository.findByEmail(dto.getEmail()).orElseThrow();

        var token = tokenManager.generateToken(user);
        authManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        log.info("Generate Token: " + dto.getEmail());
        return TokenResponse.builder().token(token).username(dto.getEmail()).name(user.getName()).surname(user.getLastName()).build();
    }
*/
}
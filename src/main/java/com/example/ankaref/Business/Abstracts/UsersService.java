package com.example.ankaref.Business.Abstracts;

import com.example.ankaref.DTO.Request.User.CreateRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.TokenResponse;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetByIdUsersResponse;

public interface UsersService {
    GetByIdUsersResponse getId(Long id);

    void creatRequest(CreateRequest createRequest);

    void updateRequest(UpdateRequest updateRequest);

    void deleteUser(Long id);

    TokenResponse login(Login login);

}

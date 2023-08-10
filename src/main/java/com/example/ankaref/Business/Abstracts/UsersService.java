package com.example.ankaref.Business.Abstracts;

import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.Login;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetByIdUsersResponse;

public interface UsersService {
   GetByIdUsersResponse getId(Long id);
   void creatRequest(CreatRequest creatRequest);
   void updateRequest(UpdateRequest updateRequest);
   void deleteUser(Long id);
   String login(Login login);

}

package com.example.ankaref.Business.Abstracts;

import com.example.ankaref.DTO.Request.User.CreatRequest;
import com.example.ankaref.DTO.Request.User.UpdateRequest;
import com.example.ankaref.DTO.Response.User.GetByIdKullanicilarResponse;

public interface UsersService {
   public GetByIdKullanicilarResponse getId(Long id);
   void creatRequest(CreatRequest creatRequest);
   void updateRequest(UpdateRequest updateRequest);
   void deleteUser(Long id);



}

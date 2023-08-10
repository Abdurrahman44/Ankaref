package com.example.ankaref.Business.Abstracts;

import com.example.ankaref.Entities.Role;

import java.util.List;

public interface RolService {
   void creatRequest(Role creatRequest);
   List<Role> getAll();

}

package com.example.ankaref.Business.Abstracts;

import com.example.ankaref.Entities.Role;

import java.util.List;
import java.util.Set;

public interface RolService {
   void creatRequest(Role creatRequest);
   List<Role> getAll();

}

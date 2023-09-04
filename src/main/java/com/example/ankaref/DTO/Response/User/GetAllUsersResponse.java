package com.example.ankaref.DTO.Response.User;

import com.example.ankaref.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse {
    private Long id;
    private String name;
    private String lastName;
    private String Email;
    private String password;
   private Set<Role> roles;

}



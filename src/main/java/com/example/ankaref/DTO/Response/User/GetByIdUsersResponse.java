package com.example.ankaref.DTO.Response.User;

import com.example.ankaref.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdUsersResponse {

    private long id;
    private String name;
    private String lastName;
    private String Email;
    private Role role;
}

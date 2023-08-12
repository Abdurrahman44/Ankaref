package com.example.ankaref.DTO.Response.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUsersResponse {
    private long id;

    private String name;

    private String lastName;
    private String Email;
}



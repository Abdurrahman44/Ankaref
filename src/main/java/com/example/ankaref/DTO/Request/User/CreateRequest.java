package com.example.ankaref.DTO.Request.User;

import com.example.ankaref.Entities.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRequest implements Serializable {//Gerekli olan Kullanıcıların özellikleri

    private String name;
    private String lastName;

    private String email;
    private String password;

}

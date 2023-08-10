package com.example.ankaref.DTO.Request.User;

import com.example.ankaref.Entities.Role;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatRequest implements Serializable {//Gerekli olan Kullanıcıların özellikleri

    private String name;
    private String lastName;
    private String Email;
    private Integer password;
    private Set<Role> roles=new HashSet<>();

}

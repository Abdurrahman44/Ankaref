package com.example.ankaref.DTO.Request.User;

import lombok.*;

import java.io.Serializable;

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

}

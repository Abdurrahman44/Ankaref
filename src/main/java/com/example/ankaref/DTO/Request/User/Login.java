package com.example.ankaref.DTO.Request.User;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {
    private String Email;
    private Integer password;
}

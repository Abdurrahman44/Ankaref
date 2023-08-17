package com.example.ankaref.DTO.Request.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRequest {

    private Long id;
    private String name;

    private String lastName;

    private String Email;

    private Integer password;


}

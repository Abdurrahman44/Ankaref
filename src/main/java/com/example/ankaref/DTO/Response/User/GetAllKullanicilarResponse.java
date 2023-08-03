package com.example.ankaref.DTO.Response.User;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllKullanicilarResponse {
    private long id;

    private String name;

    private String lastName;
    private String Email;
}
package com.example.ankaref.DTO.Request.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestE {//event Oluşturma isteği


    private String  eventName;

    private LocalDate date;

    private String explain;
}

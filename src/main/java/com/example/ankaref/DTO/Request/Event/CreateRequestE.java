package com.example.ankaref.DTO.Request.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestE {//event Oluşturma isteği


    private String  eventName;

    private String date;

    private String explain;
}

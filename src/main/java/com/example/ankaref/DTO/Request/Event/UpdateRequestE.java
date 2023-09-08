package com.example.ankaref.DTO.Request.Event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestE {
    //private Long id;
    private String  eventName;
    private LocalDate date;
    private String explain;
}

package com.example.ankaref.DTO.Request.Event;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestE {
    private String  eventName;
    private String date;
    private String explain;
}

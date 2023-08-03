package com.example.ankaref.DTO.Response.Event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdEventResponse {
    private long id;
    private String  eventName;
    private String date;
    private String explain;


}

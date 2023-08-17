package com.example.ankaref.DTO.Response.Event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllEventsResponse {
    private Long id;
    private String  eventName;
    private String date;
    private String explain;

}

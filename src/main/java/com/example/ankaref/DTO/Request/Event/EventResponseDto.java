package com.example.ankaref.DTO.Request.Event;

import lombok.Data;

import java.util.List;

@Data
public class EventResponseDto {

    private Long eventId;
    private List<Long> userIds;
}

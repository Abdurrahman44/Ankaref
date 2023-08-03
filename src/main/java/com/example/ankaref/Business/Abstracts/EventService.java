package com.example.ankaref.Business.Abstracts;

import com.example.ankaref.DTO.Request.Event.CreatRequestE;
import com.example.ankaref.DTO.Request.Event.UpdateRequestE;
import com.example.ankaref.DTO.Response.Event.GetAllEventsResponse;
import com.example.ankaref.DTO.Response.Event.GetByIdEventResponse;

import java.util.List;

public interface EventService {
    public GetByIdEventResponse getId(int id);

    List<GetAllEventsResponse> getAll();

    void creatRequest(CreatRequestE creatRequestE);

    void updateRequest(UpdateRequestE updateRequestE);

    void deleteEvent(int id);


    void sendEventNotification(int eventId);
}

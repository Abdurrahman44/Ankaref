package com.example.ankaref.Business.Abstracts;

import com.example.ankaref.DTO.Request.Event.CreateRequestE;
import com.example.ankaref.DTO.Request.Event.UpdateRequestE;
import com.example.ankaref.DTO.Response.Event.GetAllEventsResponse;
import com.example.ankaref.DTO.Response.Event.GetByIdEventResponse;
import com.example.ankaref.Entities.Event;
import org.springframework.http.ResponseEntity;



import java.util.List;

public interface EventService {
    GetByIdEventResponse getId(Long id);

    List<GetAllEventsResponse> getAll();

    void creatRequest(CreateRequestE createRequestE) ;

    void updateRequest(UpdateRequestE updateRequestE);

    void deleteEvent(Long id) ;

    void sendEventNotification(Long eventId);
   ResponseEntity<?> addUserEvent(Long eventId, Long userId );
    List<Event> upcomingEvents();

}

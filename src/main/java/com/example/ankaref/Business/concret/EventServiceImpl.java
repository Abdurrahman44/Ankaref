package com.example.ankaref.Business.concret;

import com.example.ankaref.Business.Abstracts.EventService;
import com.example.ankaref.DTO.Request.Event.CreateRequestE;
import com.example.ankaref.DTO.Request.Event.UpdateRequestE;
import com.example.ankaref.DTO.Response.Event.GetAllEventsResponse;
import com.example.ankaref.DTO.Response.Event.GetByIdEventResponse;
import com.example.ankaref.DataAccess.EventRepository;
import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Event;

import com.example.ankaref.Entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service//eventen servisi
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapperService;
    private final MailService mailService;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapperService, MailService mailService, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.modelMapperService = modelMapperService;
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    @Override
    public List<GetAllEventsResponse> getAll() {
        List<Event> events = eventRepository.findAll();
        List<GetAllEventsResponse> eventsResponse = events.stream().map(event -> modelMapperService.map(event, GetAllEventsResponse.class)).collect(Collectors.toList());
        return eventsResponse;
    }

    @Override
    public GetByIdEventResponse getId(Long id) {
        Event events = eventRepository.findById(id).orElseThrow(null);
        GetByIdEventResponse response = modelMapperService.map(events, GetByIdEventResponse.class);
        return response;
    }

    @Override
    public void creatRequest(CreateRequestE createRequestE) {
        Event event = modelMapperService.map(createRequestE, Event.class);
        if (event.getEventName() == null && event.getExplain() == null && event.getDate() == null) {
            throw new ArithmeticException("Anything is  null,Pleas check your event's blank");
        } else {
            this.eventRepository.save(event);
        }
    }

    @Override
    public void updateRequest(UpdateRequestE updateRequestE) {

        Event event = modelMapperService.map(updateRequestE, Event.class);
    }

    @Override
    public void deleteEvent(Long id) {

        if (userRepository.findById(id).isEmpty()) {
            throw new ArithmeticException("User did  not find");
        } else {
            System.out.println("Successful delete");
            this.eventRepository.deleteById(id);
        }

    }

    @Override
    public ResponseEntity<?> addUserEvent(@PathVariable Long eventId, Long userId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        Users users = userRepository.findById(userId).orElse(null);
        if (event == null || users == null) {
            return ResponseEntity.notFound().build();
        }

        event.getParticipants().add(users);
        eventRepository.save(event);
        return ResponseEntity.ok().build();


    }
@Override
public List<Event> upcomingEvents(){
    List<Event> events = eventRepository.findAll();
    List<Event> closeEvent=new ArrayList<>() ;
    Date nowTime = new Date();
    long needTime=nowTime.getTime();

    for (Event event:events) {
        long need= Long.parseLong(event.getDate());
        if(5 > need-needTime){
            closeEvent.add(event);

        }
    }
    return closeEvent;


}

    public void sendEventNotification(Long eventId) {
        eventRepository.findById(eventId).ifPresent(event -> mailService.sendEventNotification(event.getEventName(), event.getDate()));
    }
}

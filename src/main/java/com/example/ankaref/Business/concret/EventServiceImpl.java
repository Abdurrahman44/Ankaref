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
import org.springframework.web.bind.annotation.RequestBody;


import java.time.LocalDate;
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
    public void creatRequest(CreateRequestE createRequestE) {//it is work
        Event event = modelMapperService.map(createRequestE, Event.class);
        LocalDate nowTime=LocalDate.now();

        if (event.getEventName() == null && event.getExplain() == null && event.getDate() == null) {
            throw new ArithmeticException("Anything is  null,Pleas check your event's blank");
        } else if (event.getDate().isBefore(nowTime)) {
            throw new ArithmeticException("event date will not be less than current time ");
        } else {
            this.eventRepository.save(event);
        }
    }

    @Override
    public void updateRequest(UpdateRequestE updateRequestE) {//it is work
        Event event = modelMapperService.map(updateRequestE, Event.class);
        this.eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {//silme işlemi gerçekleşiyor.
        if (userRepository.findById(id).isEmpty()) {
            throw new ArithmeticException("User did  not find");
        } else {
            System.out.println("Successful delete");
            this.eventRepository.deleteById(id);
        }

    }

    @Override
    public ResponseEntity<?> addUserEvents( Long eventId,List<Long> userIds) {/*test aşamasında*/
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }
        List<Users> usersList = userRepository.findAllById(userIds);

        event.getParticipants().addAll(usersList);
        eventRepository.save(event);
        return ResponseEntity.ok().build();
    }

    @Override
public List<Event> upcomingEvents(){
    List<Event> events = eventRepository.findAll();
    List<Event> closeEvent=new ArrayList<>() ;
    LocalDate nowTime = LocalDate.now();

    for (Event event:events) {
        if (event.getDate().getDayOfYear()- nowTime.getDayOfYear()<=4 && event.getDate().getDayOfYear()- nowTime.getDayOfYear()>=0  )
            {
                closeEvent.add(event);

            }
        else if (!(event.getDate().getDayOfYear()- nowTime.getDayOfYear()>=0))
        {
            var id=event.getId();
            delete(id);

        }

    }
    return closeEvent;


}

    public void sendEventNotification(Long eventId) {
        eventRepository.findById(eventId).ifPresent(event -> mailService.sendEventNotification(event.getEventName(), String.valueOf(event.getDate())));
    }
    public void delete(Long id) {//silme işlemi gerçekleşiyor.

            this.eventRepository.deleteById(id);
            upcomingEvents();

    }




}

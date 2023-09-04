package com.example.ankaref.WeppApiControler.Event;

import com.example.ankaref.Business.Abstracts.EventService;
import com.example.ankaref.Business.concret.MailService;
import com.example.ankaref.DTO.Request.Event.CreateRequestE;
import com.example.ankaref.DTO.Request.Event.UpdateRequestE;
import com.example.ankaref.DTO.Response.Event.GetAllEventsResponse;
import com.example.ankaref.DTO.Response.Event.GetByIdEventResponse;


import com.example.ankaref.Entities.Event;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController//annatation
@RequestMapping("/api/event")
@CrossOrigin(allowedHeaders = "*", originPatterns = "*")
public class EventController {
    private final EventService eventService;
    private final MailService emailService;

    public EventController(EventService eventService, MailService emailService) {
        this.eventService = eventService;
        this.emailService = emailService;
    }

    @GetMapping("getByIdEvent/{id}")
    public GetByIdEventResponse getId(@PathVariable Long id) {

        return this.eventService.getId(id);
    }

    @GetMapping(value = "/allEvent")
    public List<GetAllEventsResponse> getAll() {

        return eventService.getAll();
    }
    @GetMapping(value = "/getCloseEvent")
    public List<Event> upcomingEvents(){
      return eventService.upcomingEvents();
    }


    @PostMapping(value = "/createEvent")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void creatEvent(@RequestBody CreateRequestE createRequestE) {
        this.eventService.creatRequest(createRequestE);

    }

    @PutMapping(value = "/updateEvent")
    public void updateEvent(@RequestBody UpdateRequestE updateRequestE) {

        this.eventService.updateRequest(updateRequestE);

    }

   @PostMapping("/addUsers")
    ResponseEntity<?> addUserEvents(@PathVariable Long eventId, @RequestBody List<Long> userIds){
        return this.eventService.addUserEvents(eventId, userIds);
    }

    @DeleteMapping()
    public void deleteEvent(@PathVariable Long id) {

        this.eventService.deleteEvent(id);
    }



    @PostMapping("/notify/{eventId}")//bildirim atma işlemini yapacak
    public void sendEventNotification(@PathVariable Long eventId) {
        eventService.sendEventNotification(eventId);
    }


}

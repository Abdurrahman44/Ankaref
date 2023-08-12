package com.example.ankaref.WeppApiControler.Event;

import com.example.ankaref.Business.Abstracts.EventService;
import com.example.ankaref.Business.concret.MailService;
import com.example.ankaref.DTO.Request.Event.CreatRequestE;
import com.example.ankaref.DTO.Request.Event.UpdateRequestE;
import com.example.ankaref.DTO.Response.Event.GetAllEventsResponse;
import com.example.ankaref.DTO.Response.Event.GetByIdEventResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController//annatation
@RequestMapping("/api/Event")
public class EventController {
    private final EventService eventService;
    private final MailService emailService;

    public EventController(EventService eventService, MailService emailService) {
        this.eventService = eventService;
        this.emailService = emailService;
    }

    @GetMapping("getByIdEvent/{id}")
    public GetByIdEventResponse getId(@PathVariable int id) {

        return this.eventService.getId(id);
    }

    @GetMapping(value = "/allEvent")
    public List<GetAllEventsResponse> getAll() {

        return eventService.getAll();
    }


    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void creatEvent(@RequestBody CreatRequestE creatRequestE) {
        this.eventService.creatRequest(creatRequestE);

    }

    @PutMapping
    public void updateEvent(@RequestBody UpdateRequestE updateRequestE) {

        this.eventService.updateRequest(updateRequestE);

    }

    @DeleteMapping
    public void deleteEvent(@PathVariable int id) {

        this.eventService.deleteEvent(id);
    }

    @PostMapping("/notify/{eventId}")//bildirim atma işlemini yapacak
    public void sendEventNotification(@PathVariable int eventId) {
        eventService.sendEventNotification(eventId);
    }


}

package com.example.ankaref.WeppApiControler.Event;

import com.example.ankaref.Business.Abstracts.EventService;
import com.example.ankaref.Business.concret.MailService;
import com.example.ankaref.DTO.Request.Event.CreatRequestE;
import com.example.ankaref.DTO.Request.Event.UpdateRequestE;
import com.example.ankaref.DTO.Response.Event.GetByIdEventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController//annatation
@RequestMapping("/api/Event")
public class EventController {
    private EventService eventService;

    public EventController(EventService eventService) {

        this.eventService = eventService;
    }

    private MailService emailService;

    @Autowired
    public EventController(MailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("getByIdEvent/{id}")
    public GetByIdEventResponse getId(@PathVariable int id) {

        return this.eventService.getId(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void CreatUser(@RequestBody CreatRequestE creatRequestE) {
        this.eventService.creatRequest(creatRequestE);

    }

    @PutMapping
    public void UpdateUser(@RequestBody UpdateRequestE updateRequestE) {

        this.eventService.updateRequest(updateRequestE);

    }

    @DeleteMapping
    public void DeleteEvent(@PathVariable int id) {

        this.eventService.deleteEvent(id);
    }

    @PostMapping("/notify/{eventId}")//bildirim atma işlemini yapacak
    public void sendEventNotification(@PathVariable int eventId) {
        eventService.sendEventNotification(eventId);
    }


}

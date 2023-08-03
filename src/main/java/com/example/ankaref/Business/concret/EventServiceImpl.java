package com.example.ankaref.Business.concret;

import com.example.ankaref.Business.Abstracts.EventService;
import com.example.ankaref.DTO.Request.Event.CreatRequestE;
import com.example.ankaref.DTO.Request.Event.UpdateRequestE;
import com.example.ankaref.DTO.Response.Event.GetAllEventsResponse;
import com.example.ankaref.DTO.Response.Event.GetByIdEventResponse;
import com.example.ankaref.DataAccess.EventRepository;
import com.example.ankaref.Entities.Event;
import com.example.ankaref.Mapper.ModelMapperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service//eventen servisi
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapperService modelMapperService;
    private  final MailService mailService;

    public EventServiceImpl(EventRepository eventRepository, ModelMapperService modelMapperService, MailService mailService) {
        this.eventRepository = eventRepository;
        this.modelMapperService = modelMapperService;
        this.mailService = mailService;
    }

    @Override
    public List<GetAllEventsResponse> getAll() {
        List<Event> events = eventRepository.findAll();
        List<GetAllEventsResponse> eventsResponse = events.stream().map(event -> this.modelMapperService.forResponse().map(event, GetAllEventsResponse.class)).collect(Collectors.toList());
        return eventsResponse;
    }

    @Override
    public GetByIdEventResponse getId(int id) {
        Event events = eventRepository.findById(id).orElseThrow();

        GetByIdEventResponse response = this.modelMapperService.forResponse().map(events, GetByIdEventResponse.class);
        return response;
    }

    @Override
    public void creatRequest(CreatRequestE creatRequestE) {
        Event event = this.modelMapperService.forRequest().map(creatRequestE, Event.class);
        this.eventRepository.save(event);
    }

    @Override
    public void updateRequest(UpdateRequestE updateRequestE) {
        Event event = this.modelMapperService.forRequest().map(updateRequestE, Event.class);
    }

    @Override
    public void deleteEvent(int id) {
        this.eventRepository.deleteById(id);

    }
    public void sendEventNotification(int eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            mailService.sendEventNotification(event.getEventName(), event.getDate());
        }
    }
}

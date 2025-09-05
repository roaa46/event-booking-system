package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.event.EventRequestDTO;
import com.worex.eventbookingsystem.dto.event.EventResponseDTO;
import com.worex.eventbookingsystem.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    // create event
    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO eventResponseDTO = eventService.saveEvent(eventRequestDTO);
        return ResponseEntity.ok(eventResponseDTO);
    }

    // view event details
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable Long id) {
        EventResponseDTO eventResponseDTO = eventService.findEvent(id);
        return ResponseEntity.ok(eventResponseDTO);
    }

    // update event
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO eventResponseDTO = eventService.updateEvent(id, eventRequestDTO);
        return ResponseEntity.ok(eventResponseDTO);
    }

    // delete event
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // view events
    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.findAllEvents();
        return ResponseEntity.ok(events);
    }
}

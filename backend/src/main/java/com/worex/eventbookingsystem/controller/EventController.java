package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.event.EventRequestDTO;
import com.worex.eventbookingsystem.dto.event.EventResponseDTO;
import com.worex.eventbookingsystem.service.EventService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    // create event
    @Transactional
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
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDTO> updateEvent(@PathVariable Long id, @RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO eventResponseDTO = eventService.updateEvent(id, eventRequestDTO);
        return ResponseEntity.ok(eventResponseDTO);
    }

    // delete event
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // view events
    @GetMapping
    public ResponseEntity<Page<EventResponseDTO>> getAllEvents( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<EventResponseDTO> eventResponseDTOPage = eventService.findAllEvents(page, size);
        return ResponseEntity.ok(eventResponseDTOPage);
    }


}

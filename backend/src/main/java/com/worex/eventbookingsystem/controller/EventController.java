package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.event.EventRequestDTO;
import com.worex.eventbookingsystem.dto.event.EventResponseDTO;
import com.worex.eventbookingsystem.service.EventService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    // create event
    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestPart("event") EventRequestDTO eventRequestDTO,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        EventResponseDTO eventResponseDTO = eventService.saveEvent(eventRequestDTO, imageFile);
        return ResponseEntity.ok(eventResponseDTO);
        }

    // update event
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EventResponseDTO> updateEvent(
            @PathVariable Long id,
            @RequestPart("event") EventRequestDTO eventRequestDTO,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        EventResponseDTO eventResponseDTO = eventService.updateEvent(id, eventRequestDTO, imageFile);
        return ResponseEntity.ok(eventResponseDTO);
    }

    // view event details
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable Long id) {
        EventResponseDTO eventResponseDTO = eventService.findEvent(id);
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
    public ResponseEntity<Page<EventResponseDTO>> getAllEvents( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
        Page<EventResponseDTO> eventResponseDTOPage = eventService.findAllEvents(page, size);
        return ResponseEntity.ok(eventResponseDTOPage);
    }


}

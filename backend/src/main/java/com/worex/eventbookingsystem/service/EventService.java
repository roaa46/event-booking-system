package com.worex.eventbookingsystem.service;

import com.worex.eventbookingsystem.dto.event.EventRequestDTO;
import com.worex.eventbookingsystem.dto.event.EventResponseDTO;
import com.worex.eventbookingsystem.mapper.EventMapper;
import com.worex.eventbookingsystem.model.Event;
import com.worex.eventbookingsystem.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    // create event
    @Transactional
    public EventResponseDTO saveEvent(EventRequestDTO eventRequestDTO) {
        Event event = eventMapper.toEntity(eventRequestDTO);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDTO(savedEvent);
    }

    // view event details
    public EventResponseDTO findEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        return eventMapper.toDTO(event);
    }

    // update event
    @Transactional
    public EventResponseDTO updateEvent(Long eventId, EventRequestDTO eventRequestDTO) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        // update fields
        existingEvent.setName(eventRequestDTO.getName());
        existingEvent.setDescription(eventRequestDTO.getDescription());
        existingEvent.setCategory(eventRequestDTO.getCategory());
        existingEvent.setDate(eventRequestDTO.getDate());
        existingEvent.setPrice(eventRequestDTO.getPrice());
        existingEvent.setImage(eventRequestDTO.getImage());
        existingEvent.setVenue(eventRequestDTO.getVenue());

        Event updatedEvent = eventRepository.save(existingEvent);
        return eventMapper.toDTO(updatedEvent);
    }

    // delete event
    @Transactional
    public void deleteEvent(Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new RuntimeException("Event not found with id: " + eventId);
        }
        eventRepository.deleteById(eventId);
    }

    // view events
    public List<EventResponseDTO> findAllEvents() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }
}

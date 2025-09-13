package com.worex.eventbookingsystem.service;

import com.worex.eventbookingsystem.dto.event.EventRequestDTO;
import com.worex.eventbookingsystem.dto.event.EventResponseDTO;
import com.worex.eventbookingsystem.mapper.EventMapper;
import com.worex.eventbookingsystem.model.Event;
import com.worex.eventbookingsystem.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@AllArgsConstructor
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    // create event
    @Transactional
    public EventResponseDTO saveEvent(EventRequestDTO eventRequestDTO, MultipartFile imageFile) {
        Event event = eventMapper.toEntity(eventRequestDTO);

        if (imageFile != null && !imageFile.isEmpty()) { // no file is sent && no content in the file
            try {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadDir = Paths.get("uploads");
                Files.createDirectories(uploadDir);

                Path filePath = uploadDir.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                event.setImage("/uploads/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Error saving image file", e);
            }
        }

        Event savedEvent = eventRepository.save(event);
        return eventMapper.toDTO(savedEvent);
    }

    // update event
    @Transactional
    public EventResponseDTO updateEvent(Long eventId, EventRequestDTO eventRequestDTO, MultipartFile imageFile) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        // update basic fields
        existingEvent.setName(eventRequestDTO.getName());
        existingEvent.setDescription(eventRequestDTO.getDescription());
        existingEvent.setCategory(eventRequestDTO.getCategory());
        existingEvent.setZonedDateTime(eventRequestDTO.getZonedDateTime());
        existingEvent.setPrice(eventRequestDTO.getPrice());
        existingEvent.setVenue(eventRequestDTO.getVenue());

        // handle image if new one is uploaded
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
                Path uploadDir = Paths.get("uploads");
                Files.createDirectories(uploadDir);

                Path filePath = uploadDir.resolve(fileName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                existingEvent.setImage("/uploads/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException("Error saving image file", e);
            }
        }

        Event updatedEvent = eventRepository.save(existingEvent);
        return eventMapper.toDTO(updatedEvent);
    }


    // view event details
    public EventResponseDTO findEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        return eventMapper.toDTO(event);
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
    public Page<EventResponseDTO> findAllEvents(int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "zonedDateTime"));
        return eventRepository.findAll(pageable).map(eventMapper::toDTO);
    }
}

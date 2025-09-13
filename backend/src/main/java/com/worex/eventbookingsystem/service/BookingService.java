package com.worex.eventbookingsystem.service;

import com.worex.eventbookingsystem.dto.booking.BookingResponseDTO;
import com.worex.eventbookingsystem.mapper.BookingMapper;
import com.worex.eventbookingsystem.model.Booking;
import com.worex.eventbookingsystem.model.Event;
import com.worex.eventbookingsystem.model.Person;
import com.worex.eventbookingsystem.repository.BookingRepository;
import com.worex.eventbookingsystem.repository.EventRepository;
import com.worex.eventbookingsystem.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final PersonRepository personRepository;
    private final BookingMapper bookingMapper;

    // book event
    @Transactional
    public BookingResponseDTO bookEvent(Long eventId, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Person not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Booking booking = bookingRepository.findByPersonAndEvent(person, event)
                .orElse( Booking.builder().person(person)
                .event(event)
                .bookingDateTime(LocalDateTime.now())
                .quantity(0)
                .build());

        booking.setQuantity(booking.getQuantity() + 1);
        booking.setBookingDateTime(LocalDateTime.now());

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toDTO(saved);
    }

    // view all bookings made by a specific user
    public Page<BookingResponseDTO> getBookingsByUser(Long personId, int page, int size) {
        if (personId == null) {
            throw new IllegalArgumentException("personId must not be null");
        }
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid page or size");
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "bookingDateTime"));
        return bookingRepository.findByPersonId(personId, pageable).map(bookingMapper::toDTO);
    }
}

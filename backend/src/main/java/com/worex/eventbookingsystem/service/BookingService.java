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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
                .bookingDate(LocalDate.now())
                .quantity(1)
                .build());

        booking.setQuantity(booking.getQuantity() + 1);
        booking.setBookingDate(LocalDate.now());

        Booking saved = bookingRepository.save(booking);
        return bookingMapper.toDTO(saved);
    }

    // view all bookings
    // may it needs changes based on authorization *************************************
    public List<BookingResponseDTO> findAllBookings() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }
}

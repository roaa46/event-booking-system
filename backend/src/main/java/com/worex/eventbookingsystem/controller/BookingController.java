package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.booking.BookingResponseDTO;
import com.worex.eventbookingsystem.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    // book event
    @PostMapping("/{eventId}")
    public ResponseEntity<BookingResponseDTO> bookEvent(@PathVariable Long eventId, @RequestParam Long personId) { // change personId when add JWT
        BookingResponseDTO responseDTO = bookingService.bookEvent(eventId, personId);
        return ResponseEntity.ok(responseDTO);
    }

    // view bookings
    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        List<BookingResponseDTO> bookings = bookingService.findAllBookings();
        return ResponseEntity.ok(bookings);
    }
}

package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.booking.BookingResponseDTO;
import com.worex.eventbookingsystem.mapper.BookingMapper;
import com.worex.eventbookingsystem.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    // book event
    @PostMapping("/persons/{personId}/bookings")
    public ResponseEntity<BookingResponseDTO> bookEvent(@PathVariable Long personId, @RequestBody Long eventId) { // change personId when add JWT
        BookingResponseDTO responseDTO = bookingService.bookEvent(eventId, personId);
        return ResponseEntity.ok(responseDTO);
    }

    // view bookings by user
    @GetMapping("/persons/{personId}/bookings")
    public ResponseEntity<Page<BookingResponseDTO>> getPersonBookings(
            @PathVariable Long personId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<BookingResponseDTO> bookingResponseDTOPage = bookingService.getBookingsByUser(personId, page, size);
        return ResponseEntity.ok(bookingResponseDTOPage);
    }
}

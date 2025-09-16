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
@RequestMapping("/api/users")
public class BookingController {
    private final BookingService bookingService;

    // book event
    @PostMapping("/{personId}/bookings")
    public ResponseEntity<BookingResponseDTO> bookEvent(@PathVariable Long personId, @RequestBody Long eventId) { // change personId when add JWT
        BookingResponseDTO responseDTO = bookingService.bookEvent(eventId, personId);
        return ResponseEntity.ok(responseDTO);
    }

    // view bookings by user
    @GetMapping("/{personId}/bookings")
    public ResponseEntity<Page<BookingResponseDTO>> getUserBookings(
            @PathVariable Long personId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        Page<BookingResponseDTO> bookingResponseDTOPage = bookingService.getBookingsByUser(personId, page, size);
        return ResponseEntity.ok(bookingResponseDTOPage);
    }
}

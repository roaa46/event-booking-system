package com.worex.eventbookingsystem.dto.booking;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BookingResponseDTO {
    private Long id;
    private Long personId;
    private String personName;
    private Long eventId;
    private String eventName;
    private LocalDateTime bookingDateTime;
    private int quantity;
}

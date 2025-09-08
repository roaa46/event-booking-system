package com.worex.eventbookingsystem.dto.booking;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BookingRequestDTO {
    private Long personId;
    private Long eventId;
    private LocalDateTime bookingDateTime;
    private int quantity;
}

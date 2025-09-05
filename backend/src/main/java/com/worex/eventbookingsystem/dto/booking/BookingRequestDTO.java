package com.worex.eventbookingsystem.dto.booking;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BookingRequestDTO {
    private Long personId;
    private Long eventId;
    private LocalDate bookingDate;
    private int quantity;
}

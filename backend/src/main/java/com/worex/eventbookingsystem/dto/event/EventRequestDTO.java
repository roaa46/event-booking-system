package com.worex.eventbookingsystem.dto.event;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EventRequestDTO {
    private String name;
    private String description;
    private String category;
    private LocalDate date;
    private String venue;
    private double price;
    private String image;
}

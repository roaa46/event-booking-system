package com.worex.eventbookingsystem.dto.event;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EventResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String category;
    private ZonedDateTime zonedDateTime;
    private String venue;
    private double price;
    private String image;
}

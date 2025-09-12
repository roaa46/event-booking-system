package com.worex.eventbookingsystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false, length = 50)
    private String category;
    @Column(name = "event_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm", timezone = "UTC")
    private ZonedDateTime zonedDateTime;
    @Column(nullable = false, length = 100)
    private String venue;
    @Column(nullable = false)
    private double price;
    private String image;
    @OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings;
}

package com.worex.eventbookingsystem.repository;

import com.worex.eventbookingsystem.model.Booking;
import com.worex.eventbookingsystem.model.Event;
import com.worex.eventbookingsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPersonAndEvent(Person person, Event event);
}

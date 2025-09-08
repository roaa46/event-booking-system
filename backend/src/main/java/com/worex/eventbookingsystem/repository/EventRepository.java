package com.worex.eventbookingsystem.repository;

import com.worex.eventbookingsystem.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Override
    Optional<Event> findById(Long id);
    boolean existsById(Long id);

}

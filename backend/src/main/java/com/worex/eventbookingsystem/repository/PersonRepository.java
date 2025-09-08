package com.worex.eventbookingsystem.repository;

import com.worex.eventbookingsystem.constant.Role;
import com.worex.eventbookingsystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<Person> findByEmail(String email);
    List<Person> findByRole(Role role);
}

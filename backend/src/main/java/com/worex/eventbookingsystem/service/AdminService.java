package com.worex.eventbookingsystem.service;

import com.worex.eventbookingsystem.constant.Role;
import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.mapper.PersonMapper;
import com.worex.eventbookingsystem.model.Person;
import com.worex.eventbookingsystem.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AdminService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    // Approve Admin
    @Transactional
    public PersonResponseDTO approveAdmin(Long adminId) {
        Person person = personRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        if (person.getRole() != Role.PENDING_ADMIN) {
            throw new IllegalStateException("Person is not a pending admin");
        }

        person.setRole(Role.ADMIN);
        personRepository.save(person);

        return personMapper.toDTO(person);
    }

    // Reject admin and set its role to user
    @Transactional
    public void rejectAdmin(Long adminId) {
        Person person = personRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        if (person.getRole() != Role.PENDING_ADMIN) {
            throw new IllegalStateException("Person is not a pending admin");
        }

        person.setRole(Role.USER);
        // personRepository.delete(person); // in case I need to delete it
        personRepository.save(person);
    }

    // Get all pending admins
    @Transactional
    public List<PersonResponseDTO> getPendingAdmins() {
        List<Person> pendingAdmins = personRepository.findByRole(Role.PENDING_ADMIN);
        return pendingAdmins.stream()
                .map(personMapper::toDTO)
                .toList();
    }
}

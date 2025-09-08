package com.worex.eventbookingsystem.service;

import com.worex.eventbookingsystem.constant.Role;
import com.worex.eventbookingsystem.dto.login.LoginRequestDTO;
import com.worex.eventbookingsystem.dto.login.LoginResponseDTO;
import com.worex.eventbookingsystem.dto.person.PersonRequestDTO;
import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.mapper.PersonMapper;
import com.worex.eventbookingsystem.model.Person;
import com.worex.eventbookingsystem.repository.PersonRepository;
import com.worex.eventbookingsystem.security.JwtUtil;
import com.worex.eventbookingsystem.util.ValidationUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register
    @Transactional
    public PersonResponseDTO createUser(PersonRequestDTO personRequestDTO) {
        if (personRepository.existsByEmail(personRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use\n");
        }
        if (personRepository.existsByUsername(personRequestDTO.getUsername())) {
            throw new IllegalArgumentException("Username already in use\n");
        }
        if (!ValidationUtils.isValidEmail(personRequestDTO.getEmail())) {
            throw new IllegalArgumentException("Invalid email format. Example: user@example.com\n");
        }
        if (!ValidationUtils.isValidUsername(personRequestDTO.getUsername())) {
            throw new IllegalArgumentException("Invalid username. Username must be 3-30 characters long, letters and numbers only\n");
        }
        if (!ValidationUtils.isValidPassword(personRequestDTO.getPassword())) {
            throw new IllegalArgumentException("Password must have at least 8 chars, upper/lowercase, number, special char\n");
        }
        if (!ValidationUtils.isValidPhone(personRequestDTO.getPhone())) {
            throw new IllegalArgumentException(
                    "Invalid phone number. Phone must contain only digits, length between 7 and 15\n"
            );
        }
        Person person = personMapper.toEntity(personRequestDTO);
        // encode password
        person.setPassword(passwordEncoder.encode(personRequestDTO.getPassword()));
        if (person.getRole() == null)
            person.setRole(Role.USER);
//        System.out.println("MAPPED ENTITY: " + person.getFirstName() + " " + person.getLastName());
        // Deactivated by default
        if (person.getRole() == Role.ADMIN)
            person.setRole(Role.PENDING_ADMIN);
        personRepository.save(person);
        return personMapper.toDTO(person);
    }

    // Login
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Person person = personRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        //compare password
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), person.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        String token = jwtUtil.generateToken(person.getEmail());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(person.getId());
        response.setUsername(person.getUsername());
        response.setRole(person.getRole().name());
        response.setFirstName(person.getFirstName());
        response.setLastName(person.getLastName());
        response.setPhone(person.getPhone());
        response.setToken(token);
        return response;
    }

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

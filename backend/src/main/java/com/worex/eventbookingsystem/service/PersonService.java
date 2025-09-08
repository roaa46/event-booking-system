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
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        Person person = personMapper.toEntity(personRequestDTO);
        // encode password
        person.setPassword(passwordEncoder.encode(personRequestDTO.getPassword()));
        if (person.getRole() == null)
            person.setRole(Role.USER);
//        System.out.println("MAPPED ENTITY: " + person.getFirstName() + " " + person.getLastName());
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
}

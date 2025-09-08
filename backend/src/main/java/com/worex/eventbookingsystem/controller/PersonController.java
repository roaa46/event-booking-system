package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.login.LoginRequestDTO;
import com.worex.eventbookingsystem.dto.login.LoginResponseDTO;
import com.worex.eventbookingsystem.dto.person.PersonRequestDTO;
import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class PersonController {
    private final PersonService personService;

    @PostMapping("/register")
    public ResponseEntity<PersonResponseDTO> register(@RequestBody PersonRequestDTO personRequestDTO) {
        PersonResponseDTO personResponseDTO = personService.createPerson(personRequestDTO);
        return ResponseEntity.ok(personResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = personService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @GetMapping("/")
    public ResponseEntity<PersonResponseDTO> viewProfile(@AuthenticationPrincipal UserDetails userDetails) {
        PersonResponseDTO profile = personService.getProfile(userDetails);
        return ResponseEntity.ok(profile);
    }
}

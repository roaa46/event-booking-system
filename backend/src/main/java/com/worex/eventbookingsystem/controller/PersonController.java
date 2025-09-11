package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.login.LoginRequestDTO;
import com.worex.eventbookingsystem.dto.login.LoginResponseDTO;
import com.worex.eventbookingsystem.dto.person.PersonRequestDTO;
import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
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
        ResponseCookie jwtCookie = ResponseCookie.from("token", loginResponseDTO.getToken())
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(24 * 60 * 60)
                .build();
        loginResponseDTO.setToken(null);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(loginResponseDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<PersonResponseDTO> viewProfile(@AuthenticationPrincipal UserDetails userDetails) {
        PersonResponseDTO profile = personService.getProfile(userDetails);
        return ResponseEntity.ok(profile);
    }
}

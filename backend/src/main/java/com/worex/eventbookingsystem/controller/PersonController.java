package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.login.LoginRequestDTO;
import com.worex.eventbookingsystem.dto.login.LoginResponseDTO;
import com.worex.eventbookingsystem.dto.person.PersonRequestDTO;
import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class PersonController {
    private final PersonService personService;

    @PostMapping("/register")
    public PersonResponseDTO register(@RequestBody PersonRequestDTO personRequestDTO) {
        return personService.createUser(personRequestDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return personService.login(loginRequestDTO);
    }
}

package com.worex.eventbookingsystem.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class LoginResponseDTO {
    private Long id;
    private String token;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String role;
}

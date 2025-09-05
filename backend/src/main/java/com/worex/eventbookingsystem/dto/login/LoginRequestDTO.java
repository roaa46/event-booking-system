package com.worex.eventbookingsystem.dto.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}

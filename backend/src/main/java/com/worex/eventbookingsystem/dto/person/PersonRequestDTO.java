package com.worex.eventbookingsystem.dto.person;

import com.worex.eventbookingsystem.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PersonRequestDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Role role;
}

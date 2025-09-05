package com.worex.eventbookingsystem.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PersonResponseDTO {
    private Long id;
    private String username;
    private String email;
}

package com.worex.eventbookingsystem.mapper;

import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.dto.person.PersonRequestDTO;
import org.mapstruct.Mapper;
import com.worex.eventbookingsystem.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toEntity(PersonRequestDTO dto);
    PersonResponseDTO toDTO(Person entity);
}

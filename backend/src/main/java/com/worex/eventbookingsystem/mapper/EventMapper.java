package com.worex.eventbookingsystem.mapper;

import com.worex.eventbookingsystem.dto.event.EventRequestDTO;
import com.worex.eventbookingsystem.dto.event.EventResponseDTO;
import com.worex.eventbookingsystem.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event toEntity(EventRequestDTO dto);
    EventResponseDTO toDTO(Event entity);
}

package com.worex.eventbookingsystem.mapper;

import com.worex.eventbookingsystem.dto.booking.BookingRequestDTO;
import com.worex.eventbookingsystem.dto.booking.BookingResponseDTO;
import com.worex.eventbookingsystem.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(BookingRequestDTO dto);

    // just in case I need more info about booking (person & event)
    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.firstName", target = "personName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.name", target = "eventName")
    BookingResponseDTO toDTO(Booking entity);

}

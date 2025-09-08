package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pending/admins")
public class AdminController {
    private final PersonService personService;

    @PutMapping("/{id}")
    public PersonResponseDTO handleAdminAction(
            @PathVariable Long id,
            @RequestParam String action) {

        switch (action.toLowerCase()) {
            case "approve":
                return personService.approveAdmin(id);
            case "reject":
                personService.rejectAdmin(id);
                return null;
            default:
                throw new IllegalArgumentException("Invalid action. Use 'approve' or 'reject'");
        }
    }

    @GetMapping("")
    public List<PersonResponseDTO> getPendingAdmins() {
        return personService.getPendingAdmins();
    }
}

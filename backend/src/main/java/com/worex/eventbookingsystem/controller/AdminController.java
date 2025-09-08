package com.worex.eventbookingsystem.controller;

import com.worex.eventbookingsystem.dto.person.PersonResponseDTO;
import com.worex.eventbookingsystem.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pending/admins")
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> handleAdminAction(
            @PathVariable Long id,
            @RequestParam String action) {

        switch (action.toLowerCase()) {
            case "approve":
                return ResponseEntity.ok(adminService.approveAdmin(id));
            case "reject":
                adminService.rejectAdmin(id);
                return ResponseEntity.noContent().build();
            default:
                throw new IllegalArgumentException("Invalid action. Use 'approve' or 'reject'");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<PersonResponseDTO>> getPendingAdmins() {
        return ResponseEntity.ok(adminService.getPendingAdmins());
    }
}

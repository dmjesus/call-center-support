package com.ubots.application.controllers;

import com.ubots.domain.services.allocation.CardIssueAllocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AllocationController {

    private final CardIssueAllocationService issueAllocationService;

    public AllocationController(CardIssueAllocationService issueAllocationService) {
        this.issueAllocationService = issueAllocationService;
    }

    @PatchMapping("/allocate/issue")
    public ResponseEntity<Object> allocateIssueToAttendant(
        @RequestParam String issueId,
        @RequestParam String attendantId
    ) {
        if (!attendantId.isEmpty() && issueAllocationService.allocate(issueId, attendantId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/allocate/issue")
    public ResponseEntity<Object> addNote(
        @RequestParam String issueId,
        @RequestBody String note
    ) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

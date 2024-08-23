package com.ubots.application.controllers;

import com.ubots.application.interfaces.IssueService;
import com.ubots.application.requests.AllocationRequest;
import com.ubots.domain.exception.BusinessServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class IssueAllocateController {

    private final IssueService cardIssueService;
    private final IssueService loanIssueService;
    private final IssueService otherIssueService;

    public IssueAllocateController(
        IssueService cardIssueService,
        IssueService loanIssueService,
        IssueService otherIssueService
    ) {
        this.cardIssueService = cardIssueService;
        this.loanIssueService = loanIssueService;
        this.otherIssueService = otherIssueService;
    }

    @PatchMapping("/allocate/issue")
    public ResponseEntity<Object> allocateIssueToAttendant(
        @RequestBody AllocationRequest request
    ) {
        try {
            return switch (request.issueType()) {
                case CARD -> ResponseEntity.ok(
                    cardIssueService.allocate(request.issueId(), request.attendantId())
                );
                case LOAN -> ResponseEntity.ok(
                    loanIssueService.allocate(request.issueId(), request.attendantId())
                );
                case OTHER -> ResponseEntity.ok(
                    otherIssueService.allocate(request.issueId(), request.attendantId())
                );
            };
        } catch (BusinessServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

package com.ubots.application.controllers;

import com.ubots.application.interfaces.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        @RequestParam String issueId,
        @RequestParam String issueType,
        @RequestParam String attendantId
    ) {
        return switch (issueType) {
            case "CARD" -> ResponseEntity.ok(cardIssueService.allocate(issueId, attendantId));
            case "LOAN" -> ResponseEntity.ok(loanIssueService.allocate(issueId, attendantId));
            case "OTHER" -> ResponseEntity.ok(otherIssueService.allocate(issueId, attendantId));
            default -> ResponseEntity.badRequest().build();
        };
    }

}

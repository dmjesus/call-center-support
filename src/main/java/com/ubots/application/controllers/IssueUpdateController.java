package com.ubots.application.controllers;

import com.ubots.application.interfaces.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class IssueUpdateController {

    private final IssueService cardIssueService;
    private final IssueService loanIssueService;
    private final IssueService otherIssueService;

    public IssueUpdateController(
        IssueService cardIssueService,
        IssueService loanIssueService,
        IssueService otherIssueService
    ) {
        this.cardIssueService = cardIssueService;
        this.loanIssueService = loanIssueService;
        this.otherIssueService = otherIssueService;
    }

    @PatchMapping("/reallocate/issue")
    public ResponseEntity<Object> reallocateIssueToAttendant(
        @RequestParam String issueId,
        @RequestParam String issueType,
        @RequestParam String attendantId
    ) {
        return switch (issueType) {
            case "CARD" -> ResponseEntity.ok(cardIssueService.reallocate(issueId, attendantId));
            case "LOAN" -> ResponseEntity.ok(loanIssueService.reallocate(issueId, attendantId));
            case "OTHER" -> ResponseEntity.ok(otherIssueService.reallocate(issueId, attendantId));
            default -> ResponseEntity.badRequest().build();
        };
    }

    @PatchMapping("/note/add")
    public ResponseEntity<Object> addNote(
        @RequestParam String issueId,
        @RequestParam String issueType,
        @RequestBody String note
    ) {
        return switch (issueType) {
            case "CARD" -> ResponseEntity.ok(cardIssueService.addNote(issueId, note));
            case "LOAN" -> ResponseEntity.ok(loanIssueService.addNote(issueId, note));
            case "OTHER" -> ResponseEntity.ok(otherIssueService.addNote(issueId, note));
            default -> ResponseEntity.badRequest().build();
        };
    }

    @PatchMapping("/close/issue")
    public ResponseEntity<Object> close(
        @RequestParam String issueId,
        @RequestParam String issueType
    ) {
        return switch (issueType) {
            case "CARD" -> ResponseEntity.ok(cardIssueService.closeIssue(issueId));
            case "LOAN" -> ResponseEntity.ok(loanIssueService.closeIssue(issueId));
            case "OTHER" -> ResponseEntity.ok(otherIssueService.closeIssue(issueId));
            default -> ResponseEntity.badRequest().build();
        };
    }
}

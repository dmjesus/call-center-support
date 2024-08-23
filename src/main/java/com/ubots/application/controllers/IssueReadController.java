package com.ubots.application.controllers;

import com.ubots.application.interfaces.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class IssueReadController {

    private final IssueService cardIssueService;
    private final IssueService loanIssueService;
    private final IssueService otherIssueService;

    public IssueReadController(
        IssueService cardIssueService,
        IssueService loanIssueService,
        IssueService otherIssueService
    ) {
        this.cardIssueService = cardIssueService;
        this.loanIssueService = loanIssueService;
        this.otherIssueService = otherIssueService;
    }

    @GetMapping("/issue/count")
    public ResponseEntity<Object> countIssues() {
        return ResponseEntity.ok(
            cardIssueService.countIssues() +
                loanIssueService.countIssues() +
                otherIssueService.countIssues()
        );
    }

    @GetMapping("/issue")
    public ResponseEntity<Object> findIssue(@RequestParam String issueId) {
        var cardIssue = cardIssueService.findIssue(issueId);

        if (cardIssue.isPresent()) {
            return ResponseEntity.ok(cardIssue.get());
        }

        var loanIssue = loanIssueService.findIssue(issueId);

        if (loanIssue.isPresent()) {
            return ResponseEntity.ok(loanIssue.get());
        }

        var otherIssue = otherIssueService.findIssue(issueId);

        return otherIssue.<ResponseEntity<Object>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.badRequest().build());

    }

}

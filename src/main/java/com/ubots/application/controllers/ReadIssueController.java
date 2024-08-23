package com.ubots.application.controllers;

import com.ubots.application.interfaces.IssueFindService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ReadIssueController {

    private final IssueFindService issueFindService;

    public ReadIssueController(IssueFindService issueFindService) {
        this.issueFindService = issueFindService;
    }

    @GetMapping("/issue/count")
    public ResponseEntity<Object> countIssues() {
        return ResponseEntity.ok(issueFindService.countIssues());
    }

    @GetMapping("/issue")
    public ResponseEntity<Object> getIssue(@RequestParam String issueId) {
        return ResponseEntity.ok(issueFindService.findIssue(issueId));
    }

}

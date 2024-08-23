package com.ubots.application.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubots.application.interfaces.IssueService;
import com.ubots.application.requests.CreateIssueRequest;
import com.ubots.domain.entities.client.Client;
import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.issue.IssueType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class IssueCreateController {

    private final IssueService cardIssueService;
    private final IssueService loanIssueService;
    private final IssueService otherIssueService;
    private final ObjectMapper mapper;

    public IssueCreateController(
        IssueService cardIssueService,
        IssueService loanIssueService,
        IssueService otherIssueService,
        ObjectMapper mapper
    ) {
        this.cardIssueService = cardIssueService;
        this.loanIssueService = loanIssueService;
        this.otherIssueService = otherIssueService;
        this.mapper = mapper;
    }

    @PostMapping("/issue")
    public ResponseEntity<Object> postMessage(@RequestBody String message) {
        try {
            var request = mapper.readValue(message, CreateIssueRequest.class);

            switch (request.type()) {
                case LOAN -> loanIssueService.create(toIssue(request));
                case CARD -> cardIssueService.create(toIssue(request));
                default -> otherIssueService.create(toIssue(request));
            }

            return ResponseEntity.ok().build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private static Issue toIssue(CreateIssueRequest request) {
        return new Issue(
            new Client(
                request.client().id(),
                request.client().firstName(),
                request.client().lastName()
            ),
            IssueType.valueOf(request.type().name()),
            request.options()
        );
    }
}

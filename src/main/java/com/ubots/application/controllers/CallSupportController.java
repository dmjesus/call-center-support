package com.ubots.application.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubots.application.interfaces.IssueService;
import com.ubots.domain.entities.client.Client;
import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.issue.IssueType;
import com.ubots.domain.entities.requests.CallMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CallSupportController {

    private final IssueService issueService;
    private final ObjectMapper mapper;

    public CallSupportController(IssueService issueService, ObjectMapper mapper) {
        this.issueService = issueService;
        this.mapper = mapper;
    }

    @PostMapping("/message")
    public ResponseEntity<Object> postMessage(@RequestBody String message) {
        try {
            var callMessage = mapper.readValue(message, CallMessage.class);
            issueService.processIssue(toIssue(callMessage));
            return ResponseEntity.ok().build();
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    private static Issue toIssue(CallMessage callMessage) {
        return new Issue(
            new Client(
                callMessage.client().id(),
                callMessage.client().firstName(),
                callMessage.client().lastName()
            ),
            IssueType.valueOf(callMessage.type().name()),
            callMessage.options()
        );
    }
}

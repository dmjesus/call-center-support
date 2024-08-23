package com.ubots.application.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubots.application.interfaces.RegisterIssueService;
import com.ubots.domain.entities.client.Client;
import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.issue.IssueType;
import com.ubots.application.requests.CallMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegisterIssueController {

    private final RegisterIssueService registerIssueService;
    private final ObjectMapper mapper;

    public RegisterIssueController(
        RegisterIssueService registerIssueService,
        ObjectMapper mapper
    ) {
        this.registerIssueService = registerIssueService;
        this.mapper = mapper;
    }

    @PostMapping("/issue")
    public ResponseEntity<Object> postMessage(@RequestBody String message) {
        try {
            var callMessage = mapper.readValue(message, CallMessage.class);
            registerIssueService.registerIssue(toIssue(callMessage));
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

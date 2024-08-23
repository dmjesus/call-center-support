package com.ubots.application.requests;

public record AllocationRequest(
    String issueId,
    IssueRequestType issueType,
    String attendantId
) { }

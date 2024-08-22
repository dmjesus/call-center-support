package com.ubots.application.interfaces;

import com.ubots.domain.entities.issue.Issue;

public interface ReadIssueService {
    Long countIssues();
    Issue readIssue(String issueId);
}

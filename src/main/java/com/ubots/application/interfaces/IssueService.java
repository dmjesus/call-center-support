package com.ubots.application.interfaces;

import com.ubots.domain.entities.issue.Issue;

public interface IssueService {
    void processIssue(Issue issue);
}

package com.ubots.application.interfaces;

import com.ubots.domain.entities.issue.Issue;
import java.util.Optional;

public interface IssueFindService {
    int countIssues();
    Optional<Issue> findIssue(String issueId);
}

package com.ubots.application.interfaces;

import com.ubots.domain.entities.issue.Issue;
import java.util.Optional;

public interface IssueConsumer {
    Optional<Issue> consume(String attendantId);
    Optional<Issue> consume(String issueId, String attendantId);
}

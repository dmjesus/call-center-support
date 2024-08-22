package com.ubots.domain.services.publishers;

import com.ubots.application.interfaces.IssuePublisher;
import com.ubots.domain.entities.issue.Issue;
import org.springframework.stereotype.Component;

@Component
public class CardIssuePublisher implements IssuePublisher {

    @Override
    public void publish(Issue issue) {

    }
}

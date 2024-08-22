package com.ubots.domain.services.publishers;

import com.ubots.application.interfaces.IssuePublisher;
import com.ubots.domain.entities.issue.Issue;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class OtherIssuesPublisher implements IssuePublisher {

    private final ArrayList<Issue> otherIssuesQueue;

    public OtherIssuesPublisher(ArrayList<Issue> otherIssuesQueue) {
        this.otherIssuesQueue = otherIssuesQueue;
    }

    @Override
    public void publish(Issue issue) {
        otherIssuesQueue.add(issue);
    }
}

package com.ubots.domain.services.publishers;

import com.ubots.application.interfaces.IssuePublisher;
import com.ubots.domain.entities.issue.Issue;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class LoanIssuesPublisher implements IssuePublisher {

    private final ArrayList<Issue> loanIssuesQueue;

    public LoanIssuesPublisher(ArrayList<Issue> loanIssuesQueue) {
        this.loanIssuesQueue = loanIssuesQueue;
    }

    @Override
    public void publish(Issue issue) {
        loanIssuesQueue.add(issue);
    }
}

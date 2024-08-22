package com.ubots.domain.services;

import com.ubots.application.interfaces.IssuePublisher;
import com.ubots.application.interfaces.RegisterIssueService;
import com.ubots.domain.entities.issue.Issue;
import org.springframework.stereotype.Service;

@Service
public class RegisterIssueServiceImpl implements RegisterIssueService {

    private final IssuePublisher cardIssuesPublisher;
    private final IssuePublisher loanIssuesPublisher;;
    private final IssuePublisher otherIssuesPublisher;;

    public RegisterIssueServiceImpl(
        IssuePublisher cardIssuesPublisher,
        IssuePublisher loanIssuesPublisher,
        IssuePublisher otherIssuesPublisher
    ) {
        this.cardIssuesPublisher = cardIssuesPublisher;
        this.loanIssuesPublisher = loanIssuesPublisher;
        this.otherIssuesPublisher = otherIssuesPublisher;
    }

    @Override
    public void registerIssue(Issue issue) {
        switch (issue.type()) {
            case CARD -> cardIssuesPublisher.publish(issue);
            case LOAN -> loanIssuesPublisher.publish(issue);
            default -> otherIssuesPublisher.publish(issue);
        }
    }
}

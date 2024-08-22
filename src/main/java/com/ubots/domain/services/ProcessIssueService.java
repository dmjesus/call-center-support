package com.ubots.domain.services;

import com.ubots.application.interfaces.IssuePublisher;
import com.ubots.application.interfaces.IssueService;
import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.services.publishers.CardIssuePublisher;
import com.ubots.domain.services.publishers.LoanIssuePublisher;
import com.ubots.domain.services.publishers.OtherIssuePublisher;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class ProcessIssueService implements IssueService {

    private final ArrayList<IssuePublisher> publishers = new ArrayList<>();

    private void processOtherMessage(Issue issue) {
        publishers.stream().filter(publisher ->
            publisher instanceof OtherIssuePublisher
        ).forEach(publisher ->
            publisher.publish(issue)
        );
    }

    private void processLoanMessage(Issue issue) {
        publishers.stream().filter(publisher ->
            publisher instanceof LoanIssuePublisher
        ).forEach(publisher ->
            publisher.publish(issue)
        );
    }

    private void processCardMessage(Issue issue) {
        publishers.stream().filter(publisher ->
            publisher instanceof CardIssuePublisher
        ).forEach(publisher ->
            publisher.publish(issue)
        );
    }

    @Override
    public void processIssue(Issue issue) {
        switch (issue.type()) {
            case CARD -> processCardMessage(issue);
            case LOAN -> processLoanMessage(issue);
            default -> processOtherMessage(issue);
        }
    }
}

package com.ubots.domain.services.publishers;

import com.ubots.application.interfaces.IssuePublisher;
import com.ubots.domain.entities.issue.Issue;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CardIssuesPublisher implements IssuePublisher {

    private final ArrayList<Issue> cardIssuesQueue;

    public CardIssuesPublisher(ArrayList<Issue> cardIssuesQueue) {
        this.cardIssuesQueue = cardIssuesQueue;
    }

    @Override
    public void publish(Issue issue) {
        cardIssuesQueue.add(issue);
        System.out.println(cardIssuesQueue);
    }
}

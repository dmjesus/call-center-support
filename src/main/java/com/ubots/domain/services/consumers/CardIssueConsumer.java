package com.ubots.domain.services.consumers;

import com.ubots.domain.entities.issue.Issue;
import java.util.LinkedList;
import org.springframework.stereotype.Component;

@Component
public class CardIssueConsumer extends DefaultConsumer {

    public CardIssueConsumer(LinkedList<Issue> cardIssuesQueue) {
        super(cardIssuesQueue);
    }
}

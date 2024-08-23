package com.ubots.domain.services.consumers;

import com.ubots.domain.entities.issue.Issue;
import java.util.LinkedList;
import org.springframework.stereotype.Component;

@Component
public class OtherIssueConsumer extends DefaultConsumer {

    public OtherIssueConsumer(LinkedList<Issue> otherIssuesQueue) {
        super(otherIssuesQueue);
    }
}

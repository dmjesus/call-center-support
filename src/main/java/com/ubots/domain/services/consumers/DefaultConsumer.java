package com.ubots.domain.services.consumers;

import com.ubots.application.interfaces.IssueConsumer;
import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.issue.IssueState;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class DefaultConsumer implements IssueConsumer {

    public final List<Issue> issuesQueue;

    public DefaultConsumer(LinkedList<Issue> issuesQueue) {
        this.issuesQueue = issuesQueue;
    }

    @Override
    public Optional<Issue> consume(String attendantId) {
        Issue issue = issuesQueue.removeFirst();
        issue.notes().add("Allocated to " + attendantId);

        Issue processingIssue = new Issue(
            issue.id(),
            issue.client(),
            issue.type(),
            issue.answers(),
            IssueState.PROCESSING,
            issue.createdAt(),
            LocalDateTime.now(),
            issue.notes(),
            attendantId
        );

        issuesQueue.add(processingIssue);

        return Optional.of(processingIssue);
    }

    @Override
    public Optional<Issue> consume(String issueId, String attendantId) {

        issuesQueue.removeIf(issue -> issue.id().equals(issueId));

        Optional<Issue> foundIssue = issuesQueue.stream().filter(issue ->
            issue.id().equals(issueId)
        ).findFirst();

        if(foundIssue.isPresent()) {
            issuesQueue.remove(foundIssue.get());
            foundIssue.get().notes().add("Allocated to " + attendantId);

            Issue presentIssue = new Issue(
                foundIssue.get().id(),
                foundIssue.get().client(),
                foundIssue.get().type(),
                foundIssue.get().answers(),
                IssueState.PROCESSING,
                foundIssue.get().createdAt(),
                LocalDateTime.now(),
                foundIssue.get().notes(),
                attendantId
            );
            issuesQueue.add(presentIssue);

            return Optional.of(presentIssue);
        }

        return foundIssue;
    }
}
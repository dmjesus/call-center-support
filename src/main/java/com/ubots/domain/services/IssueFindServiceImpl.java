package com.ubots.domain.services;

import com.ubots.application.interfaces.IssueFindService;
import com.ubots.domain.entities.issue.Issue;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class IssueFindServiceImpl implements IssueFindService {

    private final List<Issue> cardIssuesQueue;

    public IssueFindServiceImpl(List<Issue> cardIssuesQueue) {
        this.cardIssuesQueue = cardIssuesQueue;
    }

    @Override
    public int countIssues() {
        return cardIssuesQueue.size();
    }

    @Override
    public Optional<Issue> findIssue(String issueId) {
        return cardIssuesQueue.stream().filter(iss ->
            iss.id().equals(issueId)
        ).findFirst();
    }
}

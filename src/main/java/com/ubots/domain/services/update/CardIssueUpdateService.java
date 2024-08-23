package com.ubots.domain.services.update;

import com.ubots.application.interfaces.IssueFindService;
import com.ubots.domain.entities.issue.Issue;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CardIssueUpdateService extends DefaultIssueUpdateService {

    public CardIssueUpdateService(List<Issue> cardIssuesQueue, IssueFindService issueFindService) {
        super(cardIssuesQueue, issueFindService);
    }
}

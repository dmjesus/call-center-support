package com.ubots.domain.services.update;

import com.ubots.application.interfaces.IssueFindService;
import com.ubots.domain.entities.issue.Issue;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LoanIssueUpdateService extends DefaultIssueUpdateService {

    public LoanIssueUpdateService(List<Issue> loanIssuesQueue, IssueFindService issueFindService) {
        super(loanIssuesQueue, issueFindService);
    }
}

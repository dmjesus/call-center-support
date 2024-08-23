package com.ubots.domain.services.update;

import com.ubots.application.interfaces.IssueFindService;
import com.ubots.domain.entities.issue.Issue;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OtherIssueUpdateService extends DefaultIssueUpdateService {

    public OtherIssueUpdateService(List<Issue> otherIssuesQueue, IssueFindService issueFindService) {
        super(otherIssuesQueue, issueFindService);
    }
}

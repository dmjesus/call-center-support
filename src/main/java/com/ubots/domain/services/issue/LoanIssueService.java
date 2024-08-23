package com.ubots.domain.services.issue;

import com.ubots.application.interfaces.AttendantService;
import com.ubots.domain.entities.issue.Issue;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LoanIssueService extends DefaultIssueService {

    public LoanIssueService(List<Issue> loanIssuesQueue, AttendantService loanAttendantService) {
        super(loanIssuesQueue, loanAttendantService);
    }
}

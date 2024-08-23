package com.ubots.domain.services.issue;

import com.ubots.application.interfaces.AttendantService;
import com.ubots.domain.entities.issue.Issue;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OtherIssueService extends DefaultIssueService {

    public OtherIssueService(List<Issue> otherIssuesQueue, AttendantService otherAttendantService) {
        super(otherIssuesQueue, otherAttendantService);
    }
}

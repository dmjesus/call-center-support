package com.ubots.domain.services.allocation;

import com.ubots.application.interfaces.IssueConsumer;
import com.ubots.domain.entities.support.Attendant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LoanIssueAllocationService extends DefaultIssueAllocationService {

    public LoanIssueAllocationService(
        IssueConsumer loanIssueConsumer,
        List<Attendant> loanAttendants
    ) {
        super(loanIssueConsumer, loanAttendants);
    }
}

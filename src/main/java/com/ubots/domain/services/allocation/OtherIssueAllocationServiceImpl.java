package com.ubots.domain.services.allocation;

import com.ubots.application.interfaces.IssueConsumer;
import com.ubots.domain.entities.support.Attendant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OtherIssueAllocationServiceImpl extends DefaultIssueAllocationService {

    public OtherIssueAllocationServiceImpl(
        IssueConsumer otherIssueConsumer,
        List<Attendant> otherAttendants
    ) {
        super(otherIssueConsumer, otherAttendants);
    }
}

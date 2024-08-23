package com.ubots.domain.services.allocation;

import com.ubots.application.interfaces.IssueAllocationService;
import com.ubots.application.interfaces.IssueConsumer;
import com.ubots.domain.entities.support.Attendant;
import java.util.List;

public abstract class DefaultIssueAllocationService implements IssueAllocationService {

    private final IssueConsumer issueConsumer;
    private final List<Attendant> attendants;

    public DefaultIssueAllocationService(
        IssueConsumer issueConsumer,
        List<Attendant> attendants
    ) {
        this.issueConsumer = issueConsumer;
        this.attendants = attendants;
    }

    @Override
    public boolean allocate(String issueId, String attendantId) {

        if (attendantId == null || attendantId.isBlank()) {
            return false;
        }

        var attendant = attendants.stream().filter(att ->
            att.getId().toString().equals(attendantId) &&
                att.getSupportQueue().size() < 3
        ).findFirst();

        if (attendant.isPresent()) {
            var supportQueue = attendant.get().getSupportQueue();

            if (issueId == null || issueId.isBlank()) {
                issueConsumer.consume(attendantId).ifPresent(issue ->
                    supportQueue.add(issue.id())
                );
            } else {
                issueConsumer.consume(issueId, attendantId).ifPresent(issue ->
                    supportQueue.add(issue.id())
                );
            }

            return true;
        }

        return false;
    }
}


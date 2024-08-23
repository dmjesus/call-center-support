package com.ubots.domain.services.update;

import static com.ubots.domain.entities.issue.IssueState.CLOSED;

import com.ubots.application.interfaces.AttendantService;
import com.ubots.application.interfaces.IssueFindService;
import com.ubots.application.interfaces.IssueUpdateService;
import com.ubots.domain.entities.issue.Issue;
import java.time.LocalDateTime;
import java.util.List;

public abstract class DefaultIssueUpdateService implements IssueUpdateService {

    private final List<Issue> issuesQueue;
    private final IssueFindService issueFindService;
    private final AttendantService attendantService;

    public DefaultIssueUpdateService(
        List<Issue> cardIssuesQueue, IssueFindService issueFindService,
        AttendantService attendantService
    ) {
        this.issuesQueue = cardIssuesQueue;
        this.issueFindService = issueFindService;
        this.attendantService = attendantService;
    }

    @Override
    public boolean closeIssue(String issueId) {
        var issueFound = issueFindService.findIssue(issueId);
        if(issueFound.isPresent()) {
            Issue presentIssue = issueFound.get();

            if(issuesQueue.remove(presentIssue)) {
                issuesQueue.add(
                    new Issue(
                        presentIssue.id(),
                        presentIssue.client(),
                        presentIssue.type(),
                        presentIssue.answers(),
                        CLOSED,
                        presentIssue.createdAt(),
                        LocalDateTime.now(),
                        presentIssue.notes(),
                        presentIssue.attendantId()
                    )
                );
            }

            var attendant = attendantService.findAttendant(presentIssue.attendantId());

            attendant.ifPresent(att -> att.getSupportQueue().remove(presentIssue.id()));

            return true;
        }

        return false;
    }

    @Override
    public boolean addNote(String issueId, String note) {
        var issueFound = issueFindService.findIssue(issueId);

        if(issueFound.isPresent()) {
            issueFound.get().notes().add(note);
            return true;
        }

        return false;
    }

}

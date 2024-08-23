package com.ubots.domain.services.issue;

import static com.ubots.domain.entities.issue.IssueState.CLOSED;

import com.ubots.application.interfaces.AttendantService;
import com.ubots.application.interfaces.IssueService;
import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.issue.IssueState;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class DefaultIssueService implements IssueService {

    private final List<Issue> issuesQueue;
    private final AttendantService attendantService;

    public DefaultIssueService(
        List<Issue> issuesQueue,
        AttendantService attendantService
    ) {
        this.issuesQueue = issuesQueue;
        this.attendantService = attendantService;
    }

    @Override
    public void create(Issue issue) {
        issuesQueue.add(issue);
    }

    @Override
    public boolean allocate(String issueId, String attendantId) {
        if (attendantId == null || attendantId.isBlank()) {
            return false;
        }

        var attendant = attendantService.findAttendant(attendantId);

        if (attendant.isPresent()) {

            if(attendant.get().getSupportQueue().size() < 3) {

                var openIssue = issuesQueue.stream().filter(issue ->
                    issue.state() == IssueState.OPEN && issue.id().equals(issueId)
                ).findFirst();

                if(openIssue.isPresent()) {
                    var presentIssue = openIssue.get();
                    presentIssue.notes().add("Allocated to " + attendantId);

                    Issue processingIssue = new Issue(
                        presentIssue.id(),
                        presentIssue.client(),
                        presentIssue.type(),
                        presentIssue.answers(),
                        IssueState.PROCESSING,
                        presentIssue.createdAt(),
                        LocalDateTime.now(),
                        presentIssue.notes(),
                        attendant.get().getId().toString()
                    );

                    issuesQueue.add(processingIssue);
                }
                return true;
            } else {
                System.out.println("Attendant " + attendantId + " at limit capacity");
                return false;
            }
        }

        System.out.println("Attendant " + attendantId + " not found");

        return false;
    }

    @Override
    public boolean reallocate(String issueId, String newAttendantId) {
        var foundIssue = findIssue(issueId);

        if(foundIssue.isPresent()) {
            var oldAttendant = attendantService.findAttendant(foundIssue.get().attendantId());
            var newAttendant = attendantService.findAttendant(newAttendantId);

            if(newAttendant.isPresent()) {

                newAttendant.get().getSupportQueue().add(issueId);

                oldAttendant.ifPresent(attendant ->
                    attendant.getSupportQueue().removeIf(iss ->
                        iss.equals(issueId)
                    )
                );
            }

        }

        return false;
    }

    @Override
    public boolean closeIssue(String issueId) {
        var issueFound = findIssue(issueId);
        if (issueFound.isPresent()) {
            Issue presentIssue = issueFound.get();

            if (issuesQueue.remove(presentIssue)) {
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
    public int countIssues() {
        return issuesQueue.size();
    }

    @Override
    public Optional<Issue> findIssue(String issueId) {
        return issuesQueue.stream().filter(iss ->
            iss.id().equals(issueId)
        ).findFirst();
    }

    @Override
    public boolean addNote(String issueId, String note) {
        var issueFound = findIssue(issueId);

        if (issueFound.isPresent()) {
            issueFound.get().notes().add(note);
            return true;
        }

        return false;
    }

}

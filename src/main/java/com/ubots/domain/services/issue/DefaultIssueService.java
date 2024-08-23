package com.ubots.domain.services.issue;

import static com.ubots.domain.entities.issue.IssueState.CLOSED;

import com.ubots.application.interfaces.AttendantService;
import com.ubots.application.interfaces.IssueService;
import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.issue.IssueState;
import com.ubots.domain.entities.issue.IssueType;
import com.ubots.domain.exception.AttendantAtCapacity;
import com.ubots.domain.exception.InvalidInputParamsException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            throw new InvalidInputParamsException("the given attendant id is invalid");
        }

        var attendant = attendantService.findAttendant(attendantId);

        if (attendant.isPresent()) {

            if (attendant.get().getSupportQueue().size() < 3) {

                var openIssue = issuesQueue.stream().filter(issue ->
                    issue.state() == IssueState.OPEN && issue.id().equals(issueId)
                ).findFirst();

                if (openIssue.isPresent()) {
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
                    issuesQueue.removeIf(iss -> iss.id().equals(openIssue.get().id()));
                    issuesQueue.add(processingIssue);
                    attendant.get().getSupportQueue().add(processingIssue.id());
                }
                return true;
            } else {
                throw new AttendantAtCapacity("Attendant " + attendant.get().getId() + "is at capacity");
            }
        }

        throw new InvalidInputParamsException("Attendant " + attendantId + " not found");
    }

    @Override
    public boolean reallocate(String issueId, String newAttendantId) {
        var foundIssue = findIssue(issueId);

        if (foundIssue.isPresent()) {
            var oldAttendant = attendantService.findAttendant(foundIssue.get().attendantId());
            var newAttendant = attendantService.findAttendant(newAttendantId);

            if (newAttendant.isPresent()) {

                newAttendant.get().getSupportQueue().add(issueId);

                oldAttendant.ifPresent(attendant -> {
                        if (attendant.getSupportQueue().removeIf(iss -> iss.equals(issueId))) {
                            String note = "Issue reallocated from " +
                                attendant.getId() +
                                "to " +
                                newAttendant.get().getId();
                            addNote(
                                issueId,
                                note
                            );
                        }
                    }
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
                        LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
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
    public List<Issue> listIssueByType(IssueType issueType) {
        return issuesQueue.stream().filter(iss ->
            iss.type() == issueType
        ).toList();
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

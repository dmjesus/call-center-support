package com.ubots.application.interfaces;

import com.ubots.domain.entities.issue.Issue;
import java.util.Optional;

public interface IssueService {

    void create(Issue issue);

    boolean allocate(String issueId, String attendantId);

    boolean reallocate(String issueId, String newAttendantId);

    boolean closeIssue(String issueId);

    int countIssues();

    Optional<Issue> findIssue(String issueId);

    boolean addNote(String issueId, String note);
}

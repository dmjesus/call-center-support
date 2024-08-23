package com.ubots.application.interfaces;

import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.issue.IssueType;
import java.util.List;
import java.util.Optional;

public interface IssueService {

    void create(Issue issue);

    boolean allocate(String issueId, String attendantId);

    boolean reallocate(String issueId, String newAttendantId);

    boolean closeIssue(String issueId);

    int countIssues();

    Optional<Issue> findIssue(String issueId);

    List<Issue> listIssueByType(IssueType issueType);

    boolean addNote(String issueId, String note);
}

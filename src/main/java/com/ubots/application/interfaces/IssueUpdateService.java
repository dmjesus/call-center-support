package com.ubots.application.interfaces;

public interface IssueUpdateService {

    boolean closeIssue(String issueId);
    boolean addNote(String issueId, String note);
}

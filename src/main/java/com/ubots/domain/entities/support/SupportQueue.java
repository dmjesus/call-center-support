package com.ubots.domain.entities.support;

import com.ubots.domain.entities.issue.Issue;
import java.util.ArrayList;

public record SupportQueue(ArrayList<Issue> allocatedIssues) {

    public SupportQueue() {
        this(new ArrayList<>());
    }

    public boolean addIssue(final Issue issue) {
        if (allocatedIssues.size() >= 2) {
            return false;
        }

        allocatedIssues.add(issue);
        return true;
    }
}

package com.ubots.application.interfaces;

import com.ubots.domain.entities.issue.Issue;

public interface IssuePublisher {

    void publish(Issue issue);

}

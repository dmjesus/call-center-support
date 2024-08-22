package com.ubots.application.config;

import com.ubots.domain.entities.issue.Issue;
import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private final ArrayList<Issue> cardIssuesQueue = new ArrayList<>();
    private final ArrayList<Issue> loanIssuesQueue = new ArrayList<>();
    private final ArrayList<Issue> otherIssuesQueue = new ArrayList<>();

    @Bean
    public ArrayList<Issue> cardIssuesQueue() {
        return cardIssuesQueue;
    }

    @Bean
    public ArrayList<Issue> loanIssuesQueue() {
        return loanIssuesQueue;
    }

    @Bean
    public ArrayList<Issue> otherIssuesQueue() {
        return otherIssuesQueue;
    }

}

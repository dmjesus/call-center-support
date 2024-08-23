package com.ubots.application.config;

import com.ubots.domain.entities.issue.Issue;
import com.ubots.domain.entities.support.Attendant;
import com.ubots.domain.entities.support.CardAttendant;
import com.ubots.domain.entities.support.LoanAttendant;
import com.ubots.domain.entities.support.OtherAttendant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private final List<Issue> cardIssuesQueue = new LinkedList<>();
    private final List<Issue> loanIssuesQueue = new LinkedList<>();
    private final List<Issue> otherIssuesQueue = new LinkedList<>();

    private final List<Attendant> cardIssuesAttendants = new ArrayList<>();
    private final List<Attendant> loanIssuesAttendants = new ArrayList<>();
    private final List<Attendant> otherIssuesAttendants = new ArrayList<>();

    @Bean
    public List<Issue> cardIssuesQueue() {
        return cardIssuesQueue;
    }

    @Bean
    public List<Issue> loanIssuesQueue() {
        return loanIssuesQueue;
    }

    @Bean
    public List<Issue> otherIssuesQueue() {
        return otherIssuesQueue;
    }

    @Bean
    public List<Attendant> cardIssuesAttendants() {

        Attendant paulo = new CardAttendant("Paulo", "Roberto");
        Attendant joao = new CardAttendant("Joao", "Silva");

        cardIssuesAttendants.add(paulo);
        cardIssuesAttendants.add(joao);

        return cardIssuesAttendants;
    }

    @Bean
    public List<Attendant> loanIssuesAttendants() {

        Attendant renato = new LoanAttendant("Renato", "Aragao");
        Attendant fabiana = new LoanAttendant("Fabiana", "Nogueira");

        loanIssuesAttendants.add(renato);
        loanIssuesAttendants.add(fabiana);

        return loanIssuesAttendants;
    }

    @Bean
    public List<Attendant> otherIssuesAttendants() {

        Attendant carla = new OtherAttendant("Carla", "Prado");
        Attendant antonella = new OtherAttendant("Antonella", "Ferreira");
        Attendant franciele = new OtherAttendant("Franciele", "Cunha");

        otherIssuesAttendants.add(carla);
        otherIssuesAttendants.add(antonella);
        otherIssuesAttendants.add(franciele);

        return otherIssuesAttendants;
    }

}

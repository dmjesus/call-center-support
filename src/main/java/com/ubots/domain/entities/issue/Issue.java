package com.ubots.domain.entities.issue;

import com.ubots.domain.entities.client.Client;
import java.util.ArrayList;
import java.util.UUID;

public record Issue(String id, Client client, IssueType type, ArrayList<String> answers) {

    public Issue(String id, Client client, IssueType type, ArrayList<String> answers) {
        this.id = id;
        this.client = client;
        this.type = type;
        this.answers = answers;
    }

    public Issue(Client client, IssueType type, ArrayList<String> answers) {
        this(UUID.randomUUID().toString(), client, type, answers);
    }
}

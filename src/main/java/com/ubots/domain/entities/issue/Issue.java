package com.ubots.domain.entities.issue;

import static com.ubots.domain.entities.issue.IssueState.OPEN;

import com.ubots.domain.entities.client.Client;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public record Issue(
    String id,
    Client client,
    IssueType type,
    ArrayList<String> answers,
    IssueState state,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    ArrayList<String> notes,
    String attendantId
) {

    public Issue(
        String id,
        Client client,
        IssueType type,
        ArrayList<String> answers,
        IssueState state,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        ArrayList<String> notes,
        String attendantId
    ) {
        this.id = id;
        this.client = client;
        this.type = type;
        this.answers = answers;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.notes = notes;
        this.attendantId = attendantId;
    }

    public Issue(Client client, IssueType type, ArrayList<String> answers) {
        this(
            UUID.randomUUID().toString(),
            client,
            type,
            answers,
            OPEN,
            LocalDateTime.now(),
            LocalDateTime.now(),
            new ArrayList<>(),
            null
        );
    }
}

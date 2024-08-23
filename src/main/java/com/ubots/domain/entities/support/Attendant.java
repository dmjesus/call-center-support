package com.ubots.domain.entities.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class Attendant {
    protected final UUID id;
    protected String firstName;
    protected String lastName;
    protected List<String> allocatedIssues;

    public Attendant(String firstName, String lastName) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.allocatedIssues = new ArrayList<>();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getSupportQueue() {
        return allocatedIssues;
    }
}

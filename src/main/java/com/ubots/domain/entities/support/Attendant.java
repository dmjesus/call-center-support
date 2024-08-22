package com.ubots.domain.entities.support;

public abstract class Attendant {
    private final String id = null;
    private final String firstName = null;
    private final String lastName = null;
    private final SupportQueue supportQueue = new SupportQueue();

    public abstract void receiveCallMessage();
    public abstract void completeCallMessage();
}

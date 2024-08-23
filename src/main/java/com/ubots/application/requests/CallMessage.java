package com.ubots.application.requests;

import java.util.ArrayList;

public record CallMessage(ClientMessage client, MessageType type, ArrayList<String> options) { }

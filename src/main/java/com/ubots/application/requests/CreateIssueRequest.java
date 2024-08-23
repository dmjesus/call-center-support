package com.ubots.application.requests;

import java.util.ArrayList;

public record CreateIssueRequest(ClientMessage client, IssueType type, ArrayList<String> options) { }

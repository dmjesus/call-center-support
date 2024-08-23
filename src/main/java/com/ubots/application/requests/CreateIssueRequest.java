package com.ubots.application.requests;

import java.util.ArrayList;

public record CreateIssueRequest(ClientRequest client, IssueRequestType type, ArrayList<String> options) { }

package com.ubots.domain.entities.issue;

import com.ubots.domain.entities.client.Client;
import java.util.ArrayList;

public record Issue(Client client, IssueType type, ArrayList<String> answers) { }

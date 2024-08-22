package com.ubots.domain.entities.issue;

import com.ubots.domain.entities.client.Client;

public record Issue(Client client, String[] answers) { }

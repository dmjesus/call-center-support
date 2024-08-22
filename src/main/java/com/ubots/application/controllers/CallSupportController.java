package com.ubots.application.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public class CallSupportController {

    @PostMapping("/message")
    public void postMessage() {
        System.out.println("Test");
    }
}

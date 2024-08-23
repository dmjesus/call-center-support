package com.ubots.application.controllers;

import com.ubots.application.interfaces.AttendantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AttendantController {

    private final AttendantService cardAttendantService;
    private final AttendantService loanAttendantService;
    private final AttendantService otherAttendantService;

    public AttendantController(AttendantService attendantService,
        AttendantService cardAttendantService, AttendantService loanAttendantService,
        AttendantService otherAttendantService) {
        this.cardAttendantService = cardAttendantService;
        this.loanAttendantService = loanAttendantService;
        this.otherAttendantService = otherAttendantService;
    }

    @GetMapping("/attendant")
    public ResponseEntity<Object> getAttendant(
        @RequestParam String attendantId,
        @RequestParam String attendantType
    ) {

        if("CARD".equalsIgnoreCase(attendantType)) {
            return ResponseEntity.ok(cardAttendantService.findAttendant(attendantId));
        }

        if("LOAN".equalsIgnoreCase(attendantType)) {
            return ResponseEntity.ok(loanAttendantService.findAttendant(attendantId));
        }

        if("OTHER".equalsIgnoreCase(attendantType)) {
            return ResponseEntity.ok(otherAttendantService.findAttendant(attendantId));
        }

        return ResponseEntity.badRequest().build();
    }
}

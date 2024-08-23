package com.ubots.application.controllers;

import com.ubots.application.interfaces.AttendantService;
import com.ubots.domain.entities.support.Attendant;
import com.ubots.domain.entities.support.AttendantType;
import java.util.List;
import java.util.stream.Stream;
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

    public AttendantController(
        AttendantService cardAttendantService,
        AttendantService loanAttendantService,
        AttendantService otherAttendantService
    ) {
        this.cardAttendantService = cardAttendantService;
        this.loanAttendantService = loanAttendantService;
        this.otherAttendantService = otherAttendantService;
    }

    @GetMapping("/attendant/all")
    public ResponseEntity<List<Attendant>> listAttendants(
        @RequestParam(required = false) AttendantType attendantType
    ) {

        if (attendantType == null) {
            return ResponseEntity.ok(
                Stream.of(
                    cardAttendantService.listAttendants(),
                    loanAttendantService.listAttendants(),
                    otherAttendantService.listAttendants()
                ).flatMap(List::stream).toList()
            );
        }

        return switch (attendantType) {
            case CARD -> ResponseEntity.ok(cardAttendantService.listAttendants());
            case LOAN -> ResponseEntity.ok(loanAttendantService.listAttendants());
            case OTHER -> ResponseEntity.ok(otherAttendantService.listAttendants());
        };
    }

    @GetMapping("/attendant")
    public ResponseEntity<Object> listAttendants(
        @RequestParam String attendantId,
        @RequestParam AttendantType attendantType
    ) {
        return switch (attendantType) {
            case CARD -> ResponseEntity.ok(cardAttendantService.findAttendant(attendantId));
            case LOAN -> ResponseEntity.ok(loanAttendantService.findAttendant(attendantId));
            case OTHER -> ResponseEntity.ok(otherAttendantService.findAttendant(attendantId));
        };
    }
}

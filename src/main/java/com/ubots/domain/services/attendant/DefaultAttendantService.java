package com.ubots.domain.services.attendant;

import com.ubots.application.interfaces.AttendantService;
import com.ubots.domain.entities.support.Attendant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DefaultAttendantService implements AttendantService {

    protected final List<Attendant> attendantList;

    public DefaultAttendantService(List<Attendant> attendantList) {
        this.attendantList = attendantList;
    }

    @Override
    public Optional<Attendant> findAttendant(String attendantId) {
        return attendantList.stream().filter(att ->
            att.getId().equals(UUID.fromString(attendantId))
        ).findFirst();
    }

    @Override
    public Optional<Attendant> findAvailableAttendant() {
        return attendantList.stream().filter(att ->
            att.getSupportQueue().size() < 3
        ).findFirst();
    }
}

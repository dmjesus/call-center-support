package com.ubots.application.interfaces;

import com.ubots.domain.entities.support.Attendant;
import java.util.Optional;

public interface AttendantService {

    Optional<Attendant> findAttendant(String attendantId);
}

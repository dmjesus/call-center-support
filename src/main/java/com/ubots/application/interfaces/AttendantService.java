package com.ubots.application.interfaces;

import com.ubots.domain.entities.support.Attendant;
import java.util.List;
import java.util.Optional;

public interface AttendantService {

    List<Attendant> listAttendants();

    Optional<Attendant> findAttendant(String attendantId);

}

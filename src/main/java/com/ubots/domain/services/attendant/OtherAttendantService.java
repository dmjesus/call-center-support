package com.ubots.domain.services.attendant;

import com.ubots.domain.entities.support.Attendant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OtherAttendantService extends DefaultAttendantService {

    public OtherAttendantService(List<Attendant> otherIssuesAttendants) {
        super(otherIssuesAttendants);
    }
}

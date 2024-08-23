package com.ubots.domain.services.attendant;

import com.ubots.domain.entities.support.Attendant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CardAttendantService extends DefaultAttendantService {

    public CardAttendantService(List<Attendant> cardIssuesAttendants) {
        super(cardIssuesAttendants);
    }
}

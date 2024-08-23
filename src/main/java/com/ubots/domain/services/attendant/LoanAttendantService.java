package com.ubots.domain.services.attendant;

import com.ubots.domain.entities.support.Attendant;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LoanAttendantService extends DefaultAttendantService {

    public LoanAttendantService(List<Attendant> loanIssuesAttendants) {
        super(loanIssuesAttendants);
    }
}

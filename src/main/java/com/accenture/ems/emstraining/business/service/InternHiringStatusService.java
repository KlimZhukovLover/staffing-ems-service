package com.accenture.ems.emstraining.business.service;

import com.accenture.ems.emstraining.model.InternHiringStatus;

import java.util.List;
import java.util.Optional;

public interface InternHiringStatusService {

    void deleteInternHiringStatusById(Long id);

    InternHiringStatus editInternHiringStatus(InternHiringStatus internHiringStatus);

}

package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.InternHiringStatusMapStructMapper;
import com.accenture.ems.emstraining.business.mappers.InternHiringStatusMapStructMapperImpl;
import com.accenture.ems.emstraining.business.repository.model.InternHiringStatusDAO;
import com.accenture.ems.emstraining.business.repository.InternHiringStatusRepository;
import com.accenture.ems.emstraining.business.service.InternHiringStatusService;
import com.accenture.ems.emstraining.model.InternHiringStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class InternHiringStatusServiceImpl implements InternHiringStatusService {
    @Autowired
    InternHiringStatusRepository internHiringStatusRepository;
    @Autowired
    InternHiringStatusMapStructMapper mapper = new InternHiringStatusMapStructMapperImpl();

    @Override
    public void deleteInternHiringStatusById(Long id) {
        log.info("Deleting intern hiring status with id {}", id);
        internHiringStatusRepository.deleteById(id);
        log.debug("Intern hiring status with id {} was deleted", id);
    }


    @Override
    public InternHiringStatus editInternHiringStatus(InternHiringStatus internHiringStatus) {

        InternHiringStatusDAO internHiringStatusSaved = internHiringStatusRepository.save(mapper.internHiringStatusToInternHiringStatusDAO(internHiringStatus));
        return mapper.internHiringStatusDAOToInternHiringStatus(internHiringStatusSaved);
    }
}

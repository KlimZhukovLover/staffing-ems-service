package com.accenture.ems.emstraining.business.mappers;

import com.accenture.ems.emstraining.business.repository.model.InternHiringStatusDAO;
import com.accenture.ems.emstraining.model.InternHiringStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface InternHiringStatusMapStructMapper {
    InternHiringStatusDAO internHiringStatusToInternHiringStatusDAO(InternHiringStatus hiringStatus);
    InternHiringStatus internHiringStatusDAOToInternHiringStatus(InternHiringStatusDAO hiringStatusDAO);
}

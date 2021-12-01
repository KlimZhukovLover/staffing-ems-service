package com.accenture.ems.emstraining.business.service.impl;

import com.accenture.ems.emstraining.business.mappers.InternHiringStatusMapStructMapper;
import com.accenture.ems.emstraining.business.repository.InternHiringStatusDAO;
import com.accenture.ems.emstraining.business.repository.InternHiringStatusRepository;
import com.accenture.ems.emstraining.business.service.InternHiringStatusServiceImpl;
import com.accenture.ems.emstraining.model.InternHiringStatus;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
@ExtendWith(MockitoExtension.class)
public class InternHiringStatusServiceImplTest {
    @Mock
    private InternHiringStatusRepository repository;
    @Mock
    private InternHiringStatus internHiringStatus;
    @Mock
    private InternHiringStatusDAO internHiringStatusDAO;
    @InjectMocks
    private InternHiringStatusServiceImpl service;
    @Mock
    private InternHiringStatusMapStructMapper mapper;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void init() {

        internHiringStatus = createInternHiringStatus();
        internHiringStatusDAO = createInternHiringStatusDAO();

    }

    @Test
    void testDeleteEmployeeById() {

        service.deleteInternHiringStatusById(anyLong());
        verify(repository,times(1)).deleteById(anyLong());

    }

    @Test
    void testDeleteEmployeeByIdInvalid() throws Exception {

        doThrow(new IllegalArgumentException()).when(repository).deleteById(anyLong());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.deleteInternHiringStatusById(anyLong()));

    }

    @Test
    void testEditInternHiringStatus() throws Exception {

        when(repository.save(internHiringStatusDAO)).thenReturn(internHiringStatusDAO);
        when(mapper.internHiringStatusDAOToInternHiringStatus(internHiringStatusDAO)).thenReturn(internHiringStatus);
        when(mapper.internHiringStatusToInternHiringStatusDAO(internHiringStatus)).thenReturn(internHiringStatusDAO);
        InternHiringStatus internHiringStatusEdited = service.editInternHiringStatus(internHiringStatus);
        assertEquals(internHiringStatus, internHiringStatusEdited);
        verify(repository, times(1)).save(internHiringStatusDAO);

    }

    @Test
    void testEditInternHiringStatusInvalid() throws Exception {

        when(repository.save(internHiringStatusDAO)).thenThrow(new IllegalArgumentException());
        when(mapper.internHiringStatusToInternHiringStatusDAO(internHiringStatus)).thenReturn(internHiringStatusDAO);
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.editInternHiringStatus(internHiringStatus));
        verify(repository, times(1)).save(internHiringStatusDAO);

    }

    private InternHiringStatus createInternHiringStatus() {
        InternHiringStatus internHiringStatus = new InternHiringStatus();
        internHiringStatus.setId(1L);
        internHiringStatus.setStatus("hired");
        return internHiringStatus;
    }

    private InternHiringStatusDAO createInternHiringStatusDAO() {

         internHiringStatusDAO = mapper.internHiringStatusToInternHiringStatusDAO(internHiringStatus);

        return internHiringStatusDAO;
    }

}

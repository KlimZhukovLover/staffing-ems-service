package com.accenture.ems.emstraining.web.controller;

import com.accenture.ems.emstraining.business.repository.InternHiringStatusDAO;
import com.accenture.ems.emstraining.business.repository.InternHiringStatusRepository;
import com.accenture.ems.emstraining.business.service.InternHiringStatusService;
import com.accenture.ems.emstraining.controller.InternHiringStatusController;
import com.accenture.ems.emstraining.model.InternHiringStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InternHiringStatusController.class)

@SpringBootTest
public class InternHiringStatusControllerTest {
    public static String URL= "/api/v1/staffing";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InternHiringStatusController controller;
    @MockBean
    InternHiringStatusRepository internHiringStatusRepository;
    @MockBean
    private InternHiringStatusService service;

    @Test
    public void testEditInternProjectHistory() throws Exception {
        InternHiringStatus internHiringStatus =createInternHiringStatus();

        when(service.editInternHiringStatus(internHiringStatus)).thenReturn(internHiringStatus);
        when(internHiringStatusRepository.existsById(internHiringStatus.getId())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/" + internHiringStatus.getId())
                        .content(asJsonString(internHiringStatus))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(internHiringStatus.getId()))
                .andExpect(status().isCreated());


    }

    @Test
    public void testEditInternProjectHistoryInvalid() throws Exception {
        InternHiringStatus internHiringStatus = createInternHiringStatus();
        internHiringStatus.setId(null);

        when(service.editInternHiringStatus(internHiringStatus)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .put(URL + "/1")
                        .content(asJsonString(internHiringStatus))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());


    }
    @Test
    public void testDeleteInternHiringStatus() throws Exception {
        Optional<InternHiringStatusDAO> internHiringStatusDAO = Optional.of(createInternHiringStatusDA0());

        when(internHiringStatusRepository.findById(internHiringStatusDAO.get().getId())).thenReturn(internHiringStatusDAO);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL +"/"+ internHiringStatusDAO.get().getId())
                        .content(asJsonString(internHiringStatusDAO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testDeleteInternHiringStatusInvalid() throws Exception {
        Optional<InternHiringStatusDAO> internHiringStatusDAO = Optional.of(createInternHiringStatusDA0());
        internHiringStatusDAO.get().setId(10L);

        when(internHiringStatusRepository.findById(10L)).thenReturn(internHiringStatusDAO);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL +"/"+ 1L)
                        .content(asJsonString(internHiringStatusDAO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
    @Test
    public void testDeleteInternHiringStatusInvalidByIs() throws Exception {
        Optional<InternHiringStatusDAO> internHiringStatusDAO = Optional.of(createInternHiringStatusDA0());
        internHiringStatusDAO.get().setId(20L);

        when(internHiringStatusRepository.findById(20L)).thenReturn(internHiringStatusDAO);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL + null)
                        .content(asJsonString(internHiringStatusDAO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    private InternHiringStatus createInternHiringStatus() {
        InternHiringStatus internHiringStatus = new InternHiringStatus();
        internHiringStatus.setId(1L);
        internHiringStatus.setStatus("hired");
        return internHiringStatus;
    }

    private InternHiringStatusDAO createInternHiringStatusDA0() {
        InternHiringStatusDAO internHiringStatusDAO = new InternHiringStatusDAO();
        internHiringStatusDAO.setId(1L);
        internHiringStatusDAO.setStatus("hired");
        return internHiringStatusDAO;
    }
    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

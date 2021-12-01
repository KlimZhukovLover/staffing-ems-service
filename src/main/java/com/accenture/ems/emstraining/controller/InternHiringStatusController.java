package com.accenture.ems.emstraining.controller;

import com.accenture.ems.emstraining.business.repository.InternHiringStatusDAO;
import com.accenture.ems.emstraining.business.repository.InternHiringStatusRepository;
import com.accenture.ems.emstraining.business.service.InternHiringStatusServiceImpl;
import com.accenture.ems.emstraining.model.InternHiringStatus;
import com.accenture.ems.emstraining.swagger.DescriptionVariables;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Api(tags = {DescriptionVariables.INTERN_STAFFING})
@Log4j2
@RestController
@RequestMapping("/api/v1/staffing")
public class InternHiringStatusController {

    @Autowired
    private InternHiringStatusServiceImpl internHiringStatusService;
    @Autowired
    InternHiringStatusRepository internHiringStatusRepository;

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes the intern hiring status by id",
            notes = "Deletes the intern hiring status   if provided id exists",
            response = InternHiringStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The intern project history is successfully deleted"),
            @ApiResponse(code = 401, message = "The request requires user authentication"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The server has not found anything matching the Request-URI"),
            @ApiResponse(code = 500, message = "Server error")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteInternHiringStatusById(@ApiParam(value = "The id of intern hiring status", required = true)
                                                         @NonNull @PathVariable Long id){

        log.info("Delete Intern hiring status by passing ID, where ID is {}", id);
        Optional<InternHiringStatusDAO> internHiringStatus = internHiringStatusRepository.findById(id);
        if(!internHiringStatus.isPresent()){
            log.warn("Intern hiring status with id {} for delete was not found", id);
            return ResponseEntity.notFound().build();
        }
        internHiringStatusService.deleteInternHiringStatusById(id);
        log.debug("Intern hiring status with id {} was deleted {}", id, internHiringStatus);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<InternHiringStatus> editInternHiringStatus(@ApiParam(value = "id of the intern hiring status", required = true)
                                                                         @NotNull @PathVariable Long id,
                                                                         @Valid @RequestBody InternHiringStatus internHiringStatus){
        log.info("Update existing Intern hiring status with ID: {} and new body: {}", id, internHiringStatus);
        if (!internHiringStatusRepository.existsById(id) || !id.equals(internHiringStatus.getId())) {
            log.warn("Intern hiring status for update with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        internHiringStatusService.editInternHiringStatus(internHiringStatus);
        log.debug("Intern hiring status by ID:{} is updated" , internHiringStatus.getId());
        return new ResponseEntity<>(internHiringStatus,HttpStatus.CREATED);
    }
}

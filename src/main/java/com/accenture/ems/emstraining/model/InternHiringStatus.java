package com.accenture.ems.emstraining.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class InternHiringStatus {
    @ApiModelProperty(notes = "The intern hiring status ID", required = true)
    private Long Id;
    @NotNull
    @NotEmpty
    @ApiModelProperty(notes = "The Intern's hiring status", required = true)
    private String status;
}

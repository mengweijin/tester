package com.mengweijin.tester.cucumber.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class StepVariable implements Serializable {

    private Long caseId;

    private String token;

    private JdbcTemplate jdbcTemplate;

}

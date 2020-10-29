package com.mengweijin.tester.serenity.cucumber.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;

/**
 * @author mengweijin
 */
@Data
@Accessors(chain = true)
public class StepParameter implements Serializable {

    private Long caseId;

    private String token;

    private JdbcTemplate jdbcTemplate;

    private ResponseEntity<Object> responseEntity;

}

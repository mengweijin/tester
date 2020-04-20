package com.mengweijin.tester.cucumber.entity;

import cn.hutool.db.Session;
import lombok.Data;

import java.io.Serializable;

@Data
public class StepVariable implements Serializable {

    private Long caseId;

    private Session session;

    private String token;

}

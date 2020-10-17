package com.mengweijin.tester.cucumber.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author mengweijin
 */
@Data
public class TestCaseExcel implements Serializable {

    private List<TestCaseSheet> testCaseSheetList;

    private List<TestStepSheet> testStepSheetList;

}
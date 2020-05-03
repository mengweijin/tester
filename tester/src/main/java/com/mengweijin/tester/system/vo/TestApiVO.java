package com.mengweijin.tester.system.vo;

import com.mengweijin.tester.system.entity.TestApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mengweijin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestApiVO extends TestApi {

    private String projectName;

    private Integer testCaseNumber;

    private Integer testCasePassedNumber;

}

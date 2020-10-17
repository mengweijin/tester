package com.mengweijin.tester.system.vo;

import com.mengweijin.tester.system.entity.TestCase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mengweijin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestCaseVO extends TestCase {

    private String apiUrl;

}

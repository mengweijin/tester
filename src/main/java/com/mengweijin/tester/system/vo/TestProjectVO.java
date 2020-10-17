package com.mengweijin.tester.system.vo;

import com.mengweijin.tester.system.entity.TestProject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mengweijin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestProjectVO extends TestProject {

    private String dataSourceName;

}

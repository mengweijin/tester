package com.mengweijin.tester.cucumber.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mengweijin
 */
@Data
public class TestStepSheet implements Serializable {

    private Long caseId;

    @Excel(name = "CASE CODE", orderNum = "0")
    private String caseCode;

    @Excel(name = "STEP NAME", orderNum = "1")
    private String step;

    @Excel(name = "DEFAULT INDEX", orderNum = "2")
    private Integer defaultIndex;

    @Excel(name = "ASSERT KEY", orderNum = "3")
    private String assertKey;

    @Excel(name = "EXPECTED VALUE", orderNum = "4")
    private String expectValue;

}

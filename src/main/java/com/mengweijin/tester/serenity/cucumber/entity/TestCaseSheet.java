package com.mengweijin.tester.serenity.cucumber.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author mengweijin
 */
@Data
public class TestCaseSheet implements Serializable {

    private Long id;

    @Excel(name = "CASE CODE", orderNum = "0")
    private String code;

    @Excel(name = "CASE NAME", orderNum = "1")
    private String name;

    @Excel(name = "DESCRIPTION", orderNum = "2")
    private String description;

    @Excel(name = "HTTP METHOD", orderNum = "3")
    private String httpMethod;

    @Excel(name = "REQUEST URL", orderNum = "4")
    private String requestUrl;

    @Excel(name = "URL PARAMETERS", orderNum = "5")
    private String urlParams;

    @Excel(name = "REQUEST PARAMETERS", orderNum = "6")
    private String requestParams;

    @Excel(name = "PREPARED DATA SQL", orderNum = "7")
    private String preparedDataSql;

}

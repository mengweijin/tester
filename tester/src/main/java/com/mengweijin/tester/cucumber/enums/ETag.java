package com.mengweijin.tester.cucumber.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum ETag implements IEnum {

    TOKEN("@Token"),
    TRANSACTIONAL("@Transactional"),
    ROLLBACK("@Rollback"),
    COMMIT("@Commit"),
    PREPARE_DATA("@PrepareData"),
    TEST_CASE_ID("@TestCaseId"),
    END("@End");

    private String tag;

    ETag(String tag){
        this.tag = tag;
    }

    public String tag(){
        return this.tag;
    }

    @Override
    public Serializable getValue() {
        return this.name();
    }
}

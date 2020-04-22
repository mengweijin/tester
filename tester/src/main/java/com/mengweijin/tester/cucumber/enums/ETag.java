package com.mengweijin.tester.cucumber.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum ETag implements IEnum {

    TOKEN("@Token"),
    DATA_SOURCE("@DataSource"),
    PREPARE_DATA("@PrepareData"),
    RESTORE_DATA("@RestoreData");

    private String tag;

    ETag(String tag) {
        this.tag = tag;
    }

    public String tag() {
        return this.tag;
    }

    @Override
    public Serializable getValue() {
        return this.name();
    }
}

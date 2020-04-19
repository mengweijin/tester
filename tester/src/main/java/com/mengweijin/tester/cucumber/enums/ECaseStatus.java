package com.mengweijin.tester.cucumber.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;

public enum ECaseStatus implements IEnum {
    WAITING, RUNNING, SUCCESS, FAILED;

    @Override
    public Serializable getValue() {
        return this.name();
    }
}

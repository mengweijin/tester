package com.mengweijin.tester.serenity.cucumber.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.github.mengweijin.quickboot.framework.constant.Const;

import java.io.Serializable;

public enum EStep implements IEnum {

    GIVEN_TOKEN("Given", "TOKEN"),
    GIVEN_HTTP_HEADER("Given", "HTTP_HEADER"),
    WHEN_CALL_API("When", "CALL_API"),
    THEN_ASSERT_HTTP_CODE("Then", "ASSERT_HTTP_CODE"),
    THEN_ASSERT_RESPONSE("Then", "ASSERT_RESPONSE"),
    THEN_ASSERT_RESPONSE_JSON_PATH("Then", "ASSERT_RESPONSE_JSON_PATH"),
    THEN_ASSERT_DB_DATA("Then", "ASSERT_DB_DATA");

    private String stepKey;

    private String description;

    EStep(String stepKey, String description){
        this.stepKey = stepKey;
        this.description = description;
    }

    @Override
    public Serializable getValue() {
        return this.name();
    }

    public String getStepKey(){
        return this.stepKey;
    }

    public String getDescription(){
        return this.description;
    }

    public String getFeatureLine() {
        return this.stepKey + Const.SPACE + this.description;
    }
}

package com.mengweijin.tester.serenity.cucumber.util;

import com.mengweijin.tester.serenity.cucumber.entity.StepParameter;

/**
 * data source session
 * @author mengweijin
 */
public class ScenarioThreadLocal {

    private static final ThreadLocal<StepParameter> STEP_PARAMETER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(StepParameter stepParameter) {
        STEP_PARAMETER_THREAD_LOCAL.set(stepParameter);
    }

    public static StepParameter get() {
        return STEP_PARAMETER_THREAD_LOCAL.get();
    }

    public static void clear(){
        STEP_PARAMETER_THREAD_LOCAL.remove();
    }
}

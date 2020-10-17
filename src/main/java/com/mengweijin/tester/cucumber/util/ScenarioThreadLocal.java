package com.mengweijin.tester.cucumber.util;

import com.mengweijin.tester.cucumber.entity.StepVariable;

/**
 * data source session
 * @author mengweijin
 */
public class ScenarioThreadLocal {

    private static final ThreadLocal<StepVariable> threadLocal = new ThreadLocal<>();

    public static void set(StepVariable stepVariable) {
        threadLocal.set(stepVariable);
    }

    public static StepVariable get() {
        return threadLocal.get();
    }

    public static void clear(){
        threadLocal.remove();
    }
}

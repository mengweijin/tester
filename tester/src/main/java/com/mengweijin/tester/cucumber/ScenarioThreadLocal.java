package com.mengweijin.tester.cucumber;

import cn.hutool.db.Session;
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

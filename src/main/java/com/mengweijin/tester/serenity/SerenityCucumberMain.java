package com.mengweijin.tester.serenity;

import com.github.mengweijin.quickboot.framework.constant.Const;
import io.cucumber.core.options.Constants;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.util.Properties;

public class SerenityCucumberMain {

    public static final String REPORT_PROPERTY_NAME = "cucumber.report.directory";

    public static final String CUCUMBER_TMP_PATH = Const.JAVA_TMP_PATH + "cucumber" + File.separatorChar;

    public static final String CUCUMBER_FEATURE_TMP_PATH = CUCUMBER_TMP_PATH + "feature" + File.separatorChar;

    public static void runCase(File feature) {
        Properties properties = System.getProperties();
        properties.setProperty(Constants.FEATURES_PROPERTY_NAME, feature.getAbsolutePath());
        properties.setProperty(Constants.GLUE_PROPERTY_NAME, "classpath:com.mengweijin.tester.serenity.cucumber.step");
        properties.setProperty(Constants.PLUGIN_PROPERTY_NAME,
                "pretty," +
                "summary," +
                "com.mengweijin.tester.serenity.cucumber.plugin.TesterPlugin");
        properties.setProperty(REPORT_PROPERTY_NAME, "serenity-cucumber/report");

        JUnitCore.runClasses(SerenityCucumberTestSuite.class);
    }
}

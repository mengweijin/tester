package com.mengweijin.tester.cucumber;

import com.mengweijin.mwjwork.framework.constant.Const;
import io.cucumber.core.cli.Main;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CucumberUtils {

    public static final String CUCUMBER_TMP_PATH = Const.JAVA_TMP_PATH + "cucumber" + File.separatorChar;

    public static final String CUCUMBER_FEATURE_TMP_PATH = CUCUMBER_TMP_PATH + "feature" + File.separatorChar;

    /**
     *
     * @param feature feature file
     * @return
     */
    public static byte runCucumber(File feature) {
        // feature
        List<String> cucumberOptionsList = new ArrayList<>();
        cucumberOptionsList.add(feature.getAbsolutePath());
        // console output from Cucumber in a readable format.
        cucumberOptionsList.add("--monochrome");
        cucumberOptionsList.add("--glue");
        cucumberOptionsList.add("classpath:com/mengweijin/tester/cucumber");
        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("pretty");
        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("summary");
        //cucumberOptionsList.add("--plugin");
        //cucumberOptionsList.add("html:" + reportPath);
        //cucumberOptionsList.add("--plugin");
        //cucumberOptionsList.add("json:" + jsonReportFile.getAbsolutePath());
        // 自定义插件
        // cucumberOptionsList.add("--format");
        // cucumberOptionsList.add(EventListener);
        String[] argv = cucumberOptionsList.toArray(new String[0]);
        byte status = Main.run(argv, Thread.currentThread().getContextClassLoader());
        return status;
    }
}

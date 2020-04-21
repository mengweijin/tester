package com.mengweijin.tester.cucumber;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.mengweijin.mwjwork.framework.constant.Const;
import io.cucumber.core.cli.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberUtils {

    public static final String CUCUMBER_TMP_PATH = Const.JAVA_TMP_PATH + "cucumber" + File.separatorChar;

    public static final String CUCUMBER_FEATURE_TMP_PATH = CUCUMBER_TMP_PATH + "feature" + File.separatorChar;

    public static void main(String[] args) {
        runCucumber(FileUtil.file("C:\\Users\\MENGWE~1\\AppData\\Local\\Temp\\cucumber\\feature\\1\\case_1.feature"));
    }

    /**
     * @param feature feature file
     * @return
     */
    public static byte runCucumber(File feature) {
        List<String> cucumberOptionsList = new ArrayList<>();
        // feature
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
        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("com.mengweijin.tester.cucumber.TesterPlugin");
        String[] argv = cucumberOptionsList.toArray(new String[0]);
        byte status = Main.run(argv, Thread.currentThread().getContextClassLoader());
        return status;
    }

    public static String buildFeatureContent(List<String> featureContentList) {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtil.isNotEmpty(featureContentList)) {
            featureContentList.forEach(line -> builder.append(line).append("\r\n"));
        }
        return builder.toString();
    }
}

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

    public static final String CUCUMBER_JSON_REPORT_FILE_PATH = CUCUMBER_TMP_PATH + "report" + File.separatorChar + "report.json";

    /**
     *
     * @param featureDirectory feature directory
     * @param jsonReportFile json report file: c:/cucumber/report.json
     * @return
     */
    public static void runCucumber(File featureDirectory, File jsonReportFile) {
        runCli(featureDirectory,jsonReportFile);
        cucumberReporting(jsonReportFile);
    }

    private static byte runCli(File featureDirectory, File jsonReportFile) {
        // feature
        List<String> cucumberOptionsList = new ArrayList<>();
        cucumberOptionsList.add(featureDirectory.getAbsolutePath());
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
        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("json:" + jsonReportFile.getAbsolutePath());
        // 自定义插件
        // cucumberOptionsList.add("--format");
        // cucumberOptionsList.add(EventListener);
        String[] argv = cucumberOptionsList.toArray(new String[0]);
        byte status = Main.run(argv, Thread.currentThread().getContextClassLoader());
        return status;
    }

    /**
     * cucumber-reporting 美化插件
     * @param jsonReportFile JSON report file
     */
    private static void cucumberReporting(File jsonReportFile){
        String buildNumber = "1";
        String projectName = "Cucumber";

        Configuration configuration = new Configuration(jsonReportFile.getParentFile(), projectName);
        // optional configuration - check javadoc for details
        configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
        // do not make scenario failed when step has status SKIPPED
        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
        configuration.setBuildNumber(buildNumber);
        // addidtional metadata presented on main page
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Firefox");
        configuration.addClassifications("Branch", "release/1.0");

        // optionally add metadata presented on main page via properties file
        // List<String> classificationFiles = new ArrayList<>();
        // classificationFiles.add("properties-1.properties");
        // configuration.addClassificationFiles(classificationFiles);

        ReportBuilder reportBuilder = new ReportBuilder(Arrays.asList(jsonReportFile.getAbsolutePath()), configuration);
        Reportable result = reportBuilder.generateReports();
        // and here validate 'result' to decide what to do if report has failed
    }
}

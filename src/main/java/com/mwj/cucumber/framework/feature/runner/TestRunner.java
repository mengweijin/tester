package com.mwj.cucumber.framework.feature.runner;

import lombok.extern.slf4j.Slf4j;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;
import org.apache.commons.io.FileUtils;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Meng Wei Jin
 **/
@Slf4j
public class TestRunner {

    public static final String PROJECT_PATH = System.getProperty("user.dir")  + File.separatorChar;

    public static final String FEATURE_PATH = PROJECT_PATH + "feature" + File.separatorChar;

    public static final String REPORT_PATH = PROJECT_PATH + "report" + File.separatorChar;

    public static void main(String[] args) throws IOException {
        // delete directory first and create again.
        // FileUtils.deleteDirectory(FileUtils.getFile(FEATURE_PATH));
        FileUtils.deleteDirectory(FileUtils.getFile(REPORT_PATH));

        FileUtils.forceMkdir(FileUtils.getFile(FEATURE_PATH));
        FileUtils.forceMkdir(FileUtils.getFile(REPORT_PATH));

        System.setProperty("cucumber.options", buildOptions());

        JUnitCore.runClasses(CucumberRunner.class);

        // cucumber-html-reports   cucumber-reporting
        cucumberReporting();

    }

    private static String buildOptions(){
        // Overriding Cucumber Options：features = {"D:/features"} and plugin = {...}
        StringBuilder cucumberOptions = new StringBuilder();
        cucumberOptions.append("--plugin ").append("pretty").append(" ");
        cucumberOptions.append("--plugin ").append("summary").append(" ");
        cucumberOptions.append("--plugin ").append("html:").append(REPORT_PATH).append("html").append(" ");
        cucumberOptions.append("--plugin ").append("json:").append(REPORT_PATH).append("cucumber.json").append(" ");
        cucumberOptions.append(FEATURE_PATH);

        log.info("cucumber.options={}", cucumberOptions);
        return cucumberOptions.toString();
    }

    /**
     * cucumber-reporting 美化插件
     */
    private static void cucumberReporting(){
        String buildNumber = "1";
        String projectName = "cucumberProject";

        Configuration configuration = new Configuration(new File(REPORT_PATH), projectName);
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

        ReportBuilder reportBuilder = new ReportBuilder(Arrays.asList(REPORT_PATH + "cucumber.json"), configuration);
        Reportable result = reportBuilder.generateReports();
        // and here validate 'result' to decide what to do if report has failed
    }

}

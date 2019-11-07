package com.mwj.cucumber.framework.cucumber.runner;

import io.cucumber.core.cli.Main;
import lombok.extern.slf4j.Slf4j;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-11-04 21:58
 **/
@Slf4j
public class CucumberMain extends Main {

    public static final String PROJECT_PATH = System.getProperty("user.dir")  + File.separatorChar;

    public static final String CUCUMBER_PATH = PROJECT_PATH + "cucumber" + File.separatorChar;

    public static void main(String[] args) {
        try {
            run(10001L);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static byte run(Long testTaskId) throws Throwable {
        String testTaskPath = CUCUMBER_PATH + String.valueOf(testTaskId) + File.separatorChar;
        // delete directory and recreate.
        FileUtils.deleteDirectory(FileUtils.getFile(testTaskPath));
        FileUtils.forceMkdir(FileUtils.getFile(testTaskPath));

        String featurePath = testTaskPath + "feature" + File.separatorChar;
        String reportPath = testTaskPath + "report" + File.separatorChar;

        // todo 在featurePath中生成feature文件

        List<String> cucumberOptionsList = new ArrayList<>();

        cucumberOptionsList.add(featurePath);

        // console output from Cucumber in a readable format.
        cucumberOptionsList.add("--monochrome");

        cucumberOptionsList.add("--glue");
        cucumberOptionsList.add("classpath:com/mwj/cucumber/framework/cucumber/step");

        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("pretty");

        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("summary");

        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("html:" + reportPath);

        cucumberOptionsList.add("--plugin");
        cucumberOptionsList.add("json:" + reportPath + "report.json");

        String[] argv = cucumberOptionsList.toArray(new String[0]);

        byte status = run(argv, Thread.currentThread().getContextClassLoader());

        cucumberReporting(reportPath);

        return status;
    }


    /**
     * cucumber-reporting 美化插件
     */
    private static void cucumberReporting(String reportPath){
        String buildNumber = "1";
        String projectName = "cucumberProject";

        Configuration configuration = new Configuration(FileUtils.getFile(reportPath), projectName);
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

        ReportBuilder reportBuilder = new ReportBuilder(Arrays.asList(reportPath + "report.json"), configuration);
        Reportable result = reportBuilder.generateReports();
        // and here validate 'result' to decide what to do if report has failed
    }
}

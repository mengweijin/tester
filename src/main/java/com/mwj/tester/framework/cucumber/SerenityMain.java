//package com.mwj.tester.framework.cucumber;
//
//import com.google.common.base.Splitter;
//import lombok.extern.slf4j.Slf4j;
//import net.serenitybdd.core.Serenity;
//import net.serenitybdd.cucumber.cli.Main;
//import net.thucydides.core.reports.ExtendedReports;
//import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Meng Wei Jin
// * @date Create in 2019-11-04 21:58
// **/
//@Slf4j
//public class SerenityMain extends Main {
//
//    public static final String PROJECT_PATH = System.getProperty("user.dir")  + File.separatorChar;
//
//    public static final String CUCUMBER_PATH = PROJECT_PATH + "cucumber" + File.separatorChar;
//
//    public static void main(String[] args) {
//        try {
//            run(10001L);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
//
//    public static byte run(Long testTaskId) throws Throwable {
//        String testTaskPath = CUCUMBER_PATH + testTaskId + File.separatorChar;
//        // delete directory and recreate.
//        //FileUtils.deleteDirectory(FileUtils.getFile(testTaskPath));
//        FileUtils.forceMkdir(FileUtils.getFile(testTaskPath));
//
//        String featurePath = testTaskPath + "feature" + File.separatorChar;
//        String reportPath = testTaskPath + "report" + File.separatorChar;
//
//        // todo 在featurePath中生成feature文件
//
//        // feature
//        List<String> cucumberOptionsList = new ArrayList<>();
//        cucumberOptionsList.add(featurePath);
//
//        // console output from Cucumber in a readable format.
//        cucumberOptionsList.add("--monochrome");
//
//        cucumberOptionsList.add("--glue");
//        cucumberOptionsList.add("classpath:com/mwj/tester/framework/cucumber/step");
//
//        cucumberOptionsList.add("--plugin");
//        cucumberOptionsList.add("pretty");
//
//        cucumberOptionsList.add("--plugin");
//        cucumberOptionsList.add("summary");
//
//        cucumberOptionsList.add("--plugin");
//        cucumberOptionsList.add("html:" + reportPath);
//
//        cucumberOptionsList.add("--plugin");
//        cucumberOptionsList.add("json:" + reportPath + "report.json");
//
//        String[] argv = cucumberOptionsList.toArray(new String[0]);
//
//        byte status = run(argv, Thread.currentThread().getContextClassLoader());
//
//        generateHtmlStoryReports();
//        generateExtraReports();
//
//        return status;
//    }
//
//    private static HtmlAggregateStoryReporter reporter;
//    public static File sourceDirectory = FileUtils.getFile(CUCUMBER_PATH + "10001/report/serenity");
//    public static File outputDirectory = FileUtils.getFile(CUCUMBER_PATH + "10001/report/serenity");
//    public static String tags = "tag";
//
//
//    private static HtmlAggregateStoryReporter getReporter() {
//        if (reporter == null) {
//            reporter = new HtmlAggregateStoryReporter(Serenity.getDefaultProjectKey());
//        }
//        return reporter;
//    }
//
//    private static void generateHtmlStoryReports() throws IOException {
//        getReporter().setSourceDirectory(sourceDirectory);
//        getReporter().setOutputDirectory(outputDirectory);
//        //getReporter().setIssueTrackerUrl(issueTrackerUrl);
//        //getReporter().setJiraUrl(jiraUrl);
//        //getReporter().setJiraProject(jiraProject);
//        //getReporter().setJiraUsername(jiraUsername);
//        //getReporter().setJiraPassword(jiraPassword);
//        getReporter().setTags(tags);
//
//        /*if (generateOutcomes) {
//            getReporter().setGenerateTestOutcomeReports();
//        }*/
//        getReporter().generateReportsForTestResultsFrom(sourceDirectory);
//    }
//
//    public static String reports = "email";
//    private static void generateExtraReports() {
//
//        if (StringUtils.isEmpty(reports)) {
//            return;
//        }
//        List<String> extendedReportTypes = Splitter.on(",").splitToList(reports);
//        log.info("ADDITIONAL REPORTS: " + extendedReportTypes);
//        ExtendedReports.named(extendedReportTypes).forEach(
//                report -> report.generateReportFrom(sourceDirectory.toPath())
//        );
//    }
//
//}

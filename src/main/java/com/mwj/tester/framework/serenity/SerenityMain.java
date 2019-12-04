//package com.mwj.tester.framework.serenity;
//
//import com.google.common.base.Splitter;
//import lombok.extern.slf4j.Slf4j;
//import net.serenitybdd.core.Serenity;
//import net.serenitybdd.cucumber.cli.Main;
//import net.thucydides.core.ThucydidesSystemProperty;
//import net.thucydides.core.guice.Injectors;
//import net.thucydides.core.reports.ExtendedReports;
//import net.thucydides.core.reports.html.HtmlAggregateStoryReporter;
//import net.thucydides.core.util.EnvironmentVariables;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
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
//        String[] argv = cucumberOptionsList.toArray(new String[0]);
//
//        byte status = run(argv, Thread.currentThread().getContextClassLoader());
//
//        prepareExecution();
//        generateHtmlStoryReports();
//        generateExtraReports();
//        //generateCustomReports();
//
//        return status;
//    }
//
//    // prepareExecution
//    public static File outputDirectory = FileUtils.getFile(CUCUMBER_PATH + "10001/report/serenity");
//    public static File sourceDirectory = outputDirectory;
//    public static String projectKey = Serenity.getDefaultProjectKey();
//    public static String requirementsBaseDir;
//    public static void prepareExecution() {
//        System.setProperty("project.build.directory", System.getProperty("user.dir"));
//        if (!outputDirectory.exists()) {
//            outputDirectory.mkdirs();
//        }
//        configureEnvironmentVariables();
//    }
//    private static void configureEnvironmentVariables() {
//        Locale.setDefault(Locale.ENGLISH);
//        updateSystemProperty(ThucydidesSystemProperty.SERENITY_PROJECT_KEY.getPropertyName(), projectKey, Serenity.getDefaultProjectKey());
//        updateSystemProperty(ThucydidesSystemProperty.SERENITY_TEST_REQUIREMENTS_BASEDIR.toString(), requirementsBaseDir);
//    }
//    private static void updateSystemProperty(String key, String value, String defaultValue) {
//        getEnvironmentVariables().setProperty(key,
//                Optional.ofNullable(value).orElse(defaultValue));
//    }
//    private static void updateSystemProperty(String key, String value) {
//        Optional.ofNullable(value).ifPresent(
//                propertyValue -> getEnvironmentVariables().setProperty(key, propertyValue)
//        );
//    }
//    private static EnvironmentVariables environmentVariables;
//    private static EnvironmentVariables getEnvironmentVariables() {
//        if (environmentVariables == null) {
//            environmentVariables = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
//        }
//        return environmentVariables;
//    }
//
//    // generateHtmlStoryReports
//    private static HtmlAggregateStoryReporter reporter;
//    public static String tags;
//    public static boolean generateOutcomes = true;
//    private static HtmlAggregateStoryReporter getReporter() {
//        if (reporter == null) {
//            reporter = new HtmlAggregateStoryReporter(projectKey);
//        }
//        return reporter;
//    }
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
//        if (generateOutcomes) {
//            getReporter().setGenerateTestOutcomeReports();
//        }
//        getReporter().generateReportsForTestResultsFrom(sourceDirectory);
//    }
//
//    // generateExtraReports
//    public static String reports = "email";
//    private static void generateExtraReports() {
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
//
//
//
//
//
//
//}

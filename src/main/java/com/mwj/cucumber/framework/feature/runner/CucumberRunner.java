package com.mwj.cucumber.framework.feature.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * plugin summary: tell Cucumber to print code snippets for missing step definitions use the summary plugin.
 * plugin pretty:
 * plugin html:
 *
 * monochrome = true : console output from Cucumber in a readable format.
 *
 * feature:
 *
 * @author Meng Wei Jin
 * @date Create in 2019-10-26 14:08
 **/
@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, glue = {"classpath:com/mwj/cucumber/framework/feature/step"})
public class CucumberRunner {

        @BeforeClass
        public static void setup() {

        }

        @AfterClass
        public static void tearDown() {

        }

}

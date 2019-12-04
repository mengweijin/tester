package com.mwj.tester.framework.cucumber.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = "pretty",
        features="D:\\Source\\Gitee\\mwj-tester\\cucumber\\10001",
        glue = "classpath:com/mwj/tester/framework/cucumber/step")
public class Runner {
}

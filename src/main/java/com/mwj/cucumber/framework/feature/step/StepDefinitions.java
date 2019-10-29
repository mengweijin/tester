package com.mwj.cucumber.framework.feature.step;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-26 15:19
 **/
@Slf4j
public class StepDefinitions implements En {

    @Before("@Transactional")
    public void beforeScenario() {
        // todo 开启事务
        System.out.println("*****************************************todo 开启事务");
    }

    @After("@Transactional")
    public void afterScenario() {
        // todo 回滚事务
        System.out.println("*****************************************todo 回滚事务");
    }

    public StepDefinitions(){
        Given("today is Friday", () -> {
            System.out.println("*****************************************today is Friday");
        });

        When("I ask whether it's Friday yet", () -> {
            System.out.println("*****************************************I ask whether it's Friday yet");
        });

        Then("I should be told {string}", (String string) -> {
            System.out.println("*****************************************I should be told (.*)");
        });
    }


}

package com.mwj.cucumber.framework.cucumber.step;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-26 15:19
 **/
@Slf4j
public class RestfulSteps implements En {

    @Before("@PrepareData")
    public void beforeScenario() {
        // todo
        System.out.println("*****************************************todo 开启事务");
    }

    @After("@ClearData")
    public void afterScenario() {
        // todo
        System.out.println("*****************************************todo 回滚事务");
    }

    public RestfulSteps(){
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

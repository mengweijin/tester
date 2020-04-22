package com.mengweijin.tester.cucumber.step;

import com.mengweijin.tester.cucumber.entity.StepVariable;
import com.mengweijin.tester.cucumber.enums.EStep;
import com.mengweijin.tester.cucumber.util.ScenarioThreadLocal;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-26 15:19
 **/
@Slf4j
public class ApiSteps implements En {

    public ApiSteps() {

        Given(EStep.GIVEN_TOKEN.getDescription() + " {long}", (Long testCaseId) -> {
            StepVariable stepVariable = ScenarioThreadLocal.get();
            stepVariable.setToken("abc");
        });

        Given(EStep.GIVEN_HTTP_HEADER.getDescription() + " {long}", (Long testCaseId) -> {
            String token = ScenarioThreadLocal.get().getToken();
            Assert.assertEquals("abc", token);
        });

        When(EStep.WHEN_CALL_API.getDescription() + " {long}", (Long testCaseId) -> {
            System.out.println();
        });

        Then(EStep.THEN_ASSERT_HTTP_CODE.getDescription() + " {long}", (Long testCaseId) -> {
            System.out.println();
        });

        Then(EStep.THEN_ASSERT_RESPONSE.getDescription() + " {long}", (Long testCaseId) -> {
            System.out.println();
        });

        Then(EStep.THEN_ASSERT_RESPONSE_JSON_PATH.getDescription() + " {long}", (Long testCaseId) -> {
            System.out.println();
        });

        Then(EStep.THEN_ASSERT_DB_DATA.getDescription() + " {long}", (Long testCaseId) -> {
            System.out.println();
        });

    }
}

package com.mengweijin.tester.cucumber.step;

import com.mengweijin.tester.cucumber.enums.EStep;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-26 15:19
 **/
@Slf4j
public class ApiSteps implements En {

    public ApiSteps() {

        Given(EStep.GIVEN_TOKEN.getDescription(), () -> {

        });

        Given(EStep.GIVEN_HTTP_HEADER.getDescription(), () -> {

        });

        When(EStep.WHEN_CALL_API.getDescription(), () -> {

        });

        Then(EStep.THEN_ASSERT_HTTP_CODE.getDescription(), () -> {

        });

        Then(EStep.THEN_ASSERT_RESPONSE_BODY.getDescription(), () -> {

        });

        Then(EStep.THEN_ASSERT_DB_DATA.getDescription(), () -> {

        });

    }
}

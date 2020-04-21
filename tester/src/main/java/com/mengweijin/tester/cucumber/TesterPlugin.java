package com.mengweijin.tester.cucumber;

import com.mengweijin.mwjwork.framework.util.SpringUtils;
import com.mengweijin.tester.cucumber.enums.ECaseStatus;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mengweijin
 */
public class TesterPlugin implements EventListener {

    private Map<String, Object> currentStepOrHookMap = new HashMap<>();

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::testCaseResult);
    }

    private void testCaseResult(TestCaseFinished event) {
        TestCaseService testCaseService = SpringUtils.getBean(TestCaseService.class);
        Long caseId = ScenarioThreadLocal.get().getCaseId();
        TestCase testCase = new TestCase();
        testCase.setId(caseId);

        Result result = event.getResult();
        if (result.getStatus() == Status.PASSED) {
            testCase.setStatus(ECaseStatus.SUCCESS);
        } else {
            testCase.setStatus(ECaseStatus.FAILED);
            testCase.setFailedMessage(result.getError().getMessage());
        }
        testCaseService.updateById(testCase);
    }

}

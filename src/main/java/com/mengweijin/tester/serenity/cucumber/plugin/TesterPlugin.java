package com.mengweijin.tester.serenity.cucumber.plugin;

import com.github.mengweijin.quickboot.framework.util.SpringUtils;
import com.mengweijin.tester.serenity.cucumber.enums.ECaseStatus;
import com.mengweijin.tester.serenity.cucumber.util.ScenarioThreadLocal;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Result;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;

/**
 * @author mengweijin
 */
public class TesterPlugin implements EventListener {

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

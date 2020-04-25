package com.mengweijin.tester.cucumber;

import cn.hutool.core.io.FileUtil;
import com.mengweijin.mwjwork.framework.constant.Const;
import com.mengweijin.mwjwork.framework.util.SpringUtils;
import com.mengweijin.tester.cucumber.entity.StepVariable;
import com.mengweijin.tester.cucumber.enums.ECaseStatus;
import com.mengweijin.tester.cucumber.service.CucumberService;
import com.mengweijin.tester.cucumber.util.CucumberUtils;
import com.mengweijin.tester.cucumber.util.ScenarioThreadLocal;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.Future;

/**
 * @author mengweijin
 */
@Slf4j
@Component
public class AsyncFactory {

    @Async("simple")
    public Future<String> runCase(Long caseId) {
        TestCaseService testCaseService = SpringUtils.getBean(TestCaseService.class);
        CucumberService cucumberService = SpringUtils.getBean(CucumberService.class);

        TestCase testCase = new TestCase().setId(caseId);
        // 执行中
        testCase.setStatus(ECaseStatus.RUNNING);
        testCaseService.updateById(testCase);
        File feature = null;
        try {
            ScenarioThreadLocal.set(new StepVariable().setCaseId(caseId));
            feature = cucumberService.generateCaseFeature(caseId);
            CucumberUtils.runCucumber(feature);
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
            throwable.printStackTrace();
            testCase.setStatus(ECaseStatus.FAILED);
            testCase.setFailedMessage(throwable.getMessage());
            testCaseService.updateById(testCase);
        } finally {
            ScenarioThreadLocal.clear();
        }

        // 删除临时文件
        if (feature != null) {
            FileUtil.del(feature.getParentFile());
        }

        return new AsyncResult<>(Const.SUCCESS);
    }

}

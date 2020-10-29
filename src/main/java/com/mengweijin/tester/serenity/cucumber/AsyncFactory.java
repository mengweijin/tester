package com.mengweijin.tester.serenity.cucumber;

import cn.hutool.core.io.FileUtil;
import com.github.mengweijin.quickboot.framework.constant.Const;
import com.github.mengweijin.quickboot.framework.util.SpringUtils;
import com.mengweijin.tester.serenity.cucumber.entity.StepParameter;
import com.mengweijin.tester.serenity.cucumber.enums.ECaseStatus;
import com.mengweijin.tester.serenity.cucumber.service.CucumberService;
import com.mengweijin.tester.serenity.cucumber.util.ScenarioThreadLocal;
import com.mengweijin.tester.serenity.SerenityCucumberMain;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.swing.*;
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
            ScenarioThreadLocal.set(new StepParameter().setCaseId(caseId));
            feature = cucumberService.generateCaseFeature(caseId);
            SerenityCucumberMain.runCase(feature);
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
        String env = SpringUtils.getBean(ApplicationContext.class).getEnvironment().getActiveProfiles()[0];
        if (feature != null && !"dev".equals(env)) {
            FileUtil.del(feature.getParentFile());
        }

        return new AsyncResult<>(Const.SUCCESS);
    }

}

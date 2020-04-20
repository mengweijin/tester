package com.mengweijin.tester.cucumber.async;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Session;
import cn.hutool.db.ds.DSFactory;
import com.mengweijin.mwjwork.framework.constant.Const;
import com.mengweijin.mwjwork.framework.util.SpringUtils;
import com.mengweijin.tester.cucumber.CucumberService;
import com.mengweijin.tester.cucumber.CucumberUtils;
import com.mengweijin.tester.cucumber.ScenarioThreadLocal;
import com.mengweijin.tester.cucumber.entity.StepVariable;
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
        StepVariable stepVariable = new StepVariable();
        stepVariable.setCaseId(caseId);
        TestCaseService testCaseService = SpringUtils.getBean(TestCaseService.class);
        String dataSourceName = testCaseService.selectDatasourceName(caseId);
        if(StrUtil.isNotEmpty(dataSourceName)){
            Session session = Session.create(DSFactory.get(dataSourceName));
            stepVariable.setSession(session);
        }

        ScenarioThreadLocal.set(stepVariable);

        CucumberService cucumberService = SpringUtils.getBean(CucumberService.class);
        File feature = cucumberService.generateCaseFeature(caseId);
        CucumberUtils.runCucumber(feature);

        ScenarioThreadLocal.clear();

        // TODO 更新case状态，删除临时文件
        return new AsyncResult<>(Const.SUCCESS);
    }

}

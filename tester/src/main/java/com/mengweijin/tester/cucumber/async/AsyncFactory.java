package com.mengweijin.tester.cucumber.async;

import cn.hutool.core.io.FileUtil;
import com.mengweijin.mwjwork.framework.constant.Const;
import com.mengweijin.mwjwork.framework.util.SpringUtils;
import com.mengweijin.tester.cucumber.CucumberService;
import com.mengweijin.tester.cucumber.CucumberUtils;
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

    @Async("simpleAsync")
    public Future<String> runCase(Long caseId) {
        File featureDirectory = FileUtil.file(CucumberUtils.CUCUMBER_FEATURE_TMP_PATH);
        File jsonReportFile = FileUtil.file(CucumberUtils.CUCUMBER_JSON_REPORT_FILE_PATH);

        CucumberService cucumberService = SpringUtils.getBean(CucumberService.class);
        cucumberService.generateCaseFeature(caseId, featureDirectory);

        CucumberUtils.runCucumber(featureDirectory, jsonReportFile);

        // TODO 压缩，移动位置，删除临时文件
        return new AsyncResult<>(Const.SUCCESS);
    }

    @Async("simpleAsync")
    public Future<String> runApiCase(Long apiId) {
        File featureDirectory = FileUtil.file(CucumberUtils.CUCUMBER_FEATURE_TMP_PATH);
        File jsonReportFile = FileUtil.file(CucumberUtils.CUCUMBER_JSON_REPORT_FILE_PATH);

        CucumberService cucumberService = SpringUtils.getBean(CucumberService.class);
        cucumberService.generateApiFeature(apiId, featureDirectory);

        CucumberUtils.runCucumber(featureDirectory, jsonReportFile);
        return new AsyncResult<>(Const.SUCCESS);
    }
}

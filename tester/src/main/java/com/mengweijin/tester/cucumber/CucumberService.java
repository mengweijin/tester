package com.mengweijin.tester.cucumber;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.mengweijin.mwjwork.framework.constant.Const;
import com.mengweijin.tester.cucumber.enums.ETag;
import com.mengweijin.tester.system.entity.TestApi;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.entity.TestStep;
import com.mengweijin.tester.system.service.TestApiService;
import com.mengweijin.tester.system.service.TestCaseService;
import com.mengweijin.tester.system.service.TestStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CucumberService {

    @Autowired
    private TestApiService testApiService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestStepService testStepService;

    /**
     * generate feature file
     * @param apiId test case id
     * @param featureDir feature directory
     */
    public void generateApiFeature(Long apiId, File featureDir) {
        List<TestCase> testCaseList = testCaseService.lambdaQuery().eq(TestCase::getApiId, apiId).list();
        testCaseList.forEach(testCase -> {
            generateCaseFeature(testCase.getId(), featureDir);
        });
    }

    /**
     * generate feature file
     * @param caseId test case id
     * @param featureDir feature directory
     */
    public void generateCaseFeature(Long caseId, File featureDir) {
        TestCase testCase = testCaseService.getById(caseId);
        TestApi testApi = testApiService.getById(testCase.getApiId());

        List<TestStep> stepAssertList = testStepService.lambdaQuery()
                .eq(TestStep::getCaseId, caseId)
                .orderByAsc(TestStep::getDefaultIndex)
                .list();
        List<String> featureContentList = new ArrayList<>(stepAssertList.size() + 4);
        featureContentList.add("Feature: " + testCase.getName());
        featureContentList.add("");
        StringBuilder tagBuilder = new StringBuilder("    ");
        tagBuilder.append(ETag.TEST_CASE_ID.tag()).append(Const.DASH).append(caseId).append(Const.SPACE);
        tagBuilder.append(ETag.TRANSACTIONAL.tag()).append(Const.SPACE);
        if(testApi.getTransactionRollback()){
            tagBuilder.append(ETag.ROLLBACK.tag()).append(Const.SPACE);
        } else {
            tagBuilder.append(ETag.COMMIT.tag()).append(Const.SPACE);
        }
        tagBuilder.append(ETag.END.tag());
        featureContentList.add(tagBuilder.toString());
        featureContentList.add("    Scenario: " + testCase.getDescription());
        stepAssertList.forEach(testStep -> {
            featureContentList.add("        " + testStep.getStep().getFeatureLine());
        });

        log.debug("Feature content: {}", JSONObject.toJSONString(featureContentList));

        FileUtil.mkdir(featureDir);
        File featureFile = FileUtil.file(featureDir.getAbsolutePath() + File.separatorChar + "case_" + caseId + ".feature");
        FileUtil.writeLines(featureContentList, featureFile, StandardCharsets.UTF_8);
    }
}

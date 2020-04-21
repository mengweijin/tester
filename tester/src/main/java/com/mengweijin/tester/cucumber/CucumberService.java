package com.mengweijin.tester.cucumber;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
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
     * @param caseId test case id
     * @return feature file
     */
    public File generateCaseFeature(Long caseId) {
        TestCase testCase = testCaseService.getById(caseId);
        TestApi testApi = testApiService.getById(testCase.getApiId());

        List<TestStep> stepAssertList = testStepService.lambdaQuery()
                .eq(TestStep::getCaseId, caseId)
                .orderByAsc(TestStep::getIndex)
                .list();
        List<String> featureContentList = new ArrayList<>(stepAssertList.size() + 4);
        featureContentList.add("Feature: " + testCase.getName());
        featureContentList.add("");
        StringBuilder tagBuilder = new StringBuilder("    ");
        if (StrUtil.isNotBlank(testCase.getPrepareDataSql())) {
            tagBuilder.append(ETag.PREPARE_DATA.tag()).append(Const.SPACE);
        }
        tagBuilder.append(ETag.TRANSACTIONAL.tag()).append(Const.SPACE);
        if (testApi.getTransactionRollback()) {
            tagBuilder.append(ETag.ROLLBACK.tag()).append(Const.SPACE);
        } else {
            tagBuilder.append(ETag.COMMIT.tag()).append(Const.SPACE);
        }
        featureContentList.add(tagBuilder.toString());
        featureContentList.add("    Scenario Outline: " + testCase.getDescription());
        stepAssertList.forEach(testStep -> featureContentList.add("        " + testStep.getStep().getFeatureLine() + " <testCaseId>"));
        featureContentList.add("");
        featureContentList.add("        Examples:");
        featureContentList.add("            |testCaseId|");
        featureContentList.add("            |" + caseId + "|");

        String featureContent = CucumberUtils.buildFeatureContent(featureContentList);
        log.debug("Feature content: \n{}", featureContent);
        TestCase testCaseUpdate = new TestCase();
        testCaseUpdate.setId(caseId);
        testCaseUpdate.setFeature(featureContent);
        testCaseService.updateById(testCaseUpdate);

        File feature = FileUtil.file(CucumberUtils.CUCUMBER_FEATURE_TMP_PATH + caseId + File.separatorChar + "case_" + caseId + ".feature");
        FileUtil.mkdir(feature.getParentFile());
        return FileUtil.writeLines(featureContentList, feature, StandardCharsets.UTF_8);
    }
}

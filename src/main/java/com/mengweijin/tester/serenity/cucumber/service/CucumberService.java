package com.mengweijin.tester.serenity.cucumber.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.github.mengweijin.quickboot.framework.constant.Const;
import com.mengweijin.tester.serenity.cucumber.enums.ETag;
import com.mengweijin.tester.serenity.cucumber.util.CucumberUtils;
import com.mengweijin.tester.system.entity.DataSourceInfo;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.entity.TestStep;
import com.mengweijin.tester.system.service.DataSourceInfoService;
import com.mengweijin.tester.system.service.TestApiService;
import com.mengweijin.tester.system.service.TestCaseService;
import com.mengweijin.tester.system.service.TestStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengweijin
 */
@Slf4j
@Service
public class CucumberService {

    @Autowired
    private TestApiService testApiService;

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestStepService testStepService;

    @Autowired
    private DataSourceInfoService dataSourceInfoService;

    /**
     * get data source
     *
     * @param caseId test case id
     * @return DataSource
     */
    public DataSource getDataSource(Long caseId) {
        DataSourceInfo dataSourceInfo = dataSourceInfoService.getDataSourceInfoByCaseId(caseId);
        return new DriverManagerDataSource(
                dataSourceInfo.getUrl(),
                dataSourceInfo.getUsername(),
                dataSourceInfo.getPassword());
    }

    /**
     * generate feature file
     *
     * @param caseId test case id
     * @return feature file
     */
    public File generateCaseFeature(Long caseId) {
        TestCase testCase = testCaseService.getById(caseId);
        DataSourceInfo dataSourceInfo = dataSourceInfoService.getDataSourceInfoByCaseId(caseId);

        List<TestStep> stepAssertList = testStepService.lambdaQuery()
                .eq(TestStep::getCaseId, caseId)
                .orderByAsc(TestStep::getDefaultIndex)
                .list();
        List<String> featureContentList = new ArrayList<>(stepAssertList.size() + 4);
        featureContentList.add("Feature: " + testCase.getName());
        featureContentList.add("");
        StringBuilder tagBuilder = new StringBuilder("    ");
        if (dataSourceInfo != null) {
            tagBuilder.append(ETag.DATA_SOURCE.tag()).append(Const.SPACE);
        }
        if (StrUtil.isNotBlank(testCase.getPreparedDataSql())) {
            tagBuilder.append(ETag.PREPARE_DATA.tag()).append(Const.SPACE);
        }
        featureContentList.add(tagBuilder.toString());
        featureContentList.add("    Scenario Outline: " + testCase.getDescription());
        stepAssertList.forEach(testStep -> featureContentList.add("        " + testStep.getStep().getFeatureLine() + " <testCaseId> " + testStep.getId()));
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
        if(feature.exists()) {
            FileUtil.del(feature);
        } else {
            FileUtil.mkdir(feature.getParentFile());
        }
        return FileUtil.writeLines(featureContentList, feature, StandardCharsets.UTF_8);
    }
}

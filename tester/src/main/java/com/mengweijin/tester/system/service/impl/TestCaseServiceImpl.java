package com.mengweijin.tester.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.tester.cucumber.AsyncFactory;
import com.mengweijin.tester.cucumber.entity.TestCaseSheet;
import com.mengweijin.tester.cucumber.entity.TestStepSheet;
import com.mengweijin.tester.cucumber.enums.ECaseStatus;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.mapper.TestCaseMapper;
import com.mengweijin.tester.system.service.TestCaseService;
import com.mengweijin.tester.system.service.TestStepService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * test case 服务实现类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestCaseServiceImpl extends ServiceImpl<TestCaseMapper, TestCase> implements TestCaseService {

    @Autowired
    private TestStepService testStepService;

    @Autowired
    private TestCaseMapper testCaseMapper;

    @Autowired
    private AsyncFactory asyncFactory;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public void runCase(Long caseId) {
        asyncFactory.runCase(caseId);
    }

    @Override
    public void importCaseFromExcel(Long apiId, List<TestCaseSheet> testCaseSheetList, List<TestStepSheet> testStepSheetList) {
        if (CollectionUtil.isNotEmpty(testCaseSheetList)) {
            for (TestCaseSheet testCaseSheet : testCaseSheetList) {
                TestCase testCase = mapperFacade.map(testCaseSheet, TestCase.class);
                testCase.setApiId(apiId);
                testCase.setStatus(ECaseStatus.WAITING);
                this.save(testCase);

                testStepService.importStepFromExcel(testCase, testStepSheetList);
            }
        }
    }

    @Override
    public List<TestCaseSheet> getTestCaseSheetByApiId(Long apiId) {
        List<TestCase> testCaseList = this.lambdaQuery().eq(TestCase::getApiId, apiId).orderByAsc(TestCase::getCreateTime).list();
        return mapperFacade.mapAsList(testCaseList, TestCaseSheet.class);
    }
}

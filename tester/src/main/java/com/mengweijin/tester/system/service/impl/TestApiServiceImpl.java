package com.mengweijin.tester.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.tester.cucumber.AsyncFactory;
import com.mengweijin.tester.cucumber.entity.TestCaseExcel;
import com.mengweijin.tester.cucumber.entity.TestCaseSheet;
import com.mengweijin.tester.cucumber.entity.TestStepSheet;
import com.mengweijin.tester.cucumber.util.TestCaseExcelUtils;
import com.mengweijin.tester.system.entity.TestApi;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.mapper.TestApiMapper;
import com.mengweijin.tester.system.service.TestApiService;
import com.mengweijin.tester.system.service.TestCaseService;
import com.mengweijin.tester.system.service.TestStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Map;

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
public class TestApiServiceImpl extends ServiceImpl<TestApiMapper, TestApi> implements TestApiService {

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestStepService testStepService;

    @Autowired
    private TestApiMapper testApiMapper;

    @Autowired
    private AsyncFactory asyncFactory;

    @Override
    public void runApiCase(Long apiId) {
        List<TestCase> testCaseList = testCaseService.lambdaQuery().eq(TestCase::getApiId, apiId).list();
        testCaseList.forEach(testCase -> asyncFactory.runCase(testCase.getId()));
    }

    @Override
    public void importCaseFromExcel(Long apiId, List<File> fileList) {
        if (CollectionUtil.isNotEmpty(fileList)) {
            TestCaseExcel testCaseExcel;
            List<TestCaseSheet> testCaseSheetList;
            List<TestStepSheet> testStepSheetList;
            for (File file : fileList) {
                testCaseExcel = TestCaseExcelUtils.importTestCaseFromExcel(file);
                testCaseSheetList = testCaseExcel.getTestCaseSheetList();
                testStepSheetList = testCaseExcel.getTestStepSheetList();

                testCaseService.importCaseFromExcel(apiId, testCaseSheetList, testStepSheetList);
            }
        }
    }

    @Override
    public TestCaseExcel getTestCaseExcel(Long apiId) {
        TestCaseExcel testCaseExcel = new TestCaseExcel();
        List<TestCaseSheet> testCaseSheetList = testCaseService.getTestCaseSheetByApiId(apiId);
        List<TestStepSheet> testStepSheetList = testStepService.getTestStepSheetByApiId(apiId);

        testCaseExcel.setTestCaseSheetList(testCaseSheetList);
        testCaseExcel.setTestStepSheetList(testStepSheetList);
        return testCaseExcel;
    }

    @Override
    public IPage<Map<String, Object>> selectPageVO(IPage<Map<String, Object>> page, TestApi testApi) {
        return testApiMapper.selectPageVO(page, testApi);
    }
}

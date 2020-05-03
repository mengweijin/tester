package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.cucumber.entity.TestCaseSheet;
import com.mengweijin.tester.cucumber.entity.TestStepSheet;
import com.mengweijin.tester.system.entity.TestCase;

import java.util.List;

/**
 * <p>
 * test case 服务类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
public interface TestCaseService extends IService<TestCase> {

    void runCase(Long caseId);

    void importCaseFromExcel(Long apiId, List<TestCaseSheet> testCaseSheetList, List<TestStepSheet> testStepSheetList);

    List<TestCaseSheet> getTestCaseSheetByApiId(Long apiId);

}

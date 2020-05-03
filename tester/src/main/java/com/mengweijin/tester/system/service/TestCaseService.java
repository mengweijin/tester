package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.cucumber.entity.TestCaseSheet;
import com.mengweijin.tester.cucumber.entity.TestStepSheet;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.vo.TestCaseVO;

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

    /**
     * run test case
     * @param caseId case id
     */
    void runCase(Long caseId);

    /**
     * Import test case from excel
     * @param apiId apiId
     * @param testCaseSheetList test case sheet list
     * @param testStepSheetList test step sheet list
     */
    void importCaseFromExcel(Long apiId, List<TestCaseSheet> testCaseSheetList, List<TestStepSheet> testStepSheetList);

    /**
     * get test case sheet by api id
     * @param apiId api id
     * @return list
     */
    List<TestCaseSheet> getTestCaseSheetByApiId(Long apiId);

    /**
     * get test  case count by api id
     * @param apiId api id
     * @return float
     */
    int getTestCaseCountByApiId(Long apiId);

    /**
     * get test case passed number by apiId
     * @param apiId api id
     * @return int
     */
    int getTestCasePassedNumberByApiId(Long apiId);

    /**
     * select test case page VO
     * @param page page
     * @param testCase test case
     * @return page
     */
    IPage<TestCaseVO> selectPageVO(IPage<TestCase> page, TestCase testCase);
}

package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.cucumber.entity.TestStepSheet;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.entity.TestStep;

import java.util.List;

/**
 * <p>
 * test step 服务类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
public interface TestStepService extends IService<TestStep> {

    void importStepFromExcel(TestCase testCase, List<TestStepSheet> testStepSheetList);

    List<TestStepSheet> getTestStepSheetByApiId(Long apiId);
}

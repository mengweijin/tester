package com.mengweijin.tester.system.service;

import com.mengweijin.tester.system.entity.TestCase;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * test case 服务类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
public interface TestCaseService extends IService<TestCase> {

    String selectDatasourceName(Long caseId);

    void runCase(Long caseId);
}

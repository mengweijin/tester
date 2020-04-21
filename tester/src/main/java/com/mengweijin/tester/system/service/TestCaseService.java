package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.system.entity.TestCase;

/**
 * <p>
 * test case 服务类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
public interface TestCaseService extends IService<TestCase> {

    String selectDataSourceName(Long caseId);

    void runCase(Long caseId);
}

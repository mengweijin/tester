package com.mengweijin.tester.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.tester.cucumber.async.AsyncFactory;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.mapper.TestCaseMapper;
import com.mengweijin.tester.system.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * test case 服务实现类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
@Service
public class TestCaseServiceImpl extends ServiceImpl<TestCaseMapper, TestCase> implements TestCaseService {

    @Autowired
    private TestCaseMapper testCaseMapper;

    @Autowired
    private AsyncFactory asyncFactory;

    @Override
    public String selectDataSourceName(Long caseId) {
        return testCaseMapper.selectDataSourceName(caseId);
    }

    @Override
    public void runCase(Long caseId) {
        asyncFactory.runCase(caseId);
    }
}

package com.mengweijin.tester.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.tester.cucumber.async.AsyncFactory;
import com.mengweijin.tester.system.entity.TestApi;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.mapper.TestApiMapper;
import com.mengweijin.tester.system.service.TestApiService;
import com.mengweijin.tester.system.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TestApiServiceImpl extends ServiceImpl<TestApiMapper, TestApi> implements TestApiService {

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private TestApiMapper testApiMapper;

    @Autowired
    private AsyncFactory asyncFactory;

    @Override
    public void runApiCase(Long apiId) {
        List<TestCase> testCaseList = testCaseService.lambdaQuery().eq(TestCase::getApiId, apiId).list();
        testCaseList.forEach(testCase -> asyncFactory.runCase(testCase.getId()));
    }
}

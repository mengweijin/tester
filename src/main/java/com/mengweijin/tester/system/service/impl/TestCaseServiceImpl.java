package com.mengweijin.tester.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mengweijin.quickboot.mybatis.page.Pager;
import com.mengweijin.tester.serenity.cucumber.AsyncFactory;
import com.mengweijin.tester.serenity.cucumber.entity.TestCaseSheet;
import com.mengweijin.tester.serenity.cucumber.entity.TestStepSheet;
import com.mengweijin.tester.serenity.cucumber.enums.ECaseStatus;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.mapper.TestCaseMapper;
import com.mengweijin.tester.system.service.TestApiService;
import com.mengweijin.tester.system.service.TestCaseService;
import com.mengweijin.tester.system.service.TestStepService;
import com.mengweijin.tester.system.vo.TestCaseVO;
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
    private TestApiService testApiService;

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

                testStepService.importStepFromExcel(testCaseSheet.getCode(), testCase, testStepSheetList);
            }
        }
    }

    @Override
    public List<TestCaseSheet> getTestCaseSheetByApiId(Long apiId) {
        List<TestCase> testCaseList = this.lambdaQuery().eq(TestCase::getApiId, apiId).orderByAsc(TestCase::getCreateTime).list();
        List<TestCaseSheet> testCaseSheetList = mapperFacade.mapAsList(testCaseList, TestCaseSheet.class);
        testCaseSheetList.forEach(testCaseSheet -> testCaseSheet.setCode(String.valueOf(testCaseSheet.getId())));
        return testCaseSheetList;
    }

    @Override
    public int getTestCaseCountByApiId(Long apiId) {
        return this.lambdaQuery().eq(TestCase::getApiId, apiId).count();
    }

    @Override
    public int getTestCasePassedNumberByApiId(Long apiId) {
        return this.lambdaQuery().eq(TestCase::getApiId, apiId).eq(TestCase::getStatus, ECaseStatus.SUCCESS).count();
    }

    @Override
    public IPage<TestCaseVO> selectPageVO(IPage<TestCase> page, TestCase testCase) {
        IPage<TestCase> testCasePage = this.page(page, new QueryWrapper<>(testCase).orderByDesc("create_time"));

        Pager<TestCaseVO> pager = new Pager<>();
        pager.setCurrent(testCasePage.getCurrent());
        pager.setSize(testCasePage.getSize());
        pager.setTotal(testCasePage.getTotal());

        List<TestCase> testCaseList = testCasePage.getRecords();
        List<TestCaseVO> testCaseVOList = mapperFacade.mapAsList(testCaseList, TestCaseVO.class);
        testCaseVOList.forEach(vo -> vo.setApiUrl(testApiService.getById(vo.getApiId()).getUrl()));
        pager.setRecords(testCaseVOList);
        return pager;
    }
}

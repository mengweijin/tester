package com.mengweijin.tester.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.tester.cucumber.entity.TestStepSheet;
import com.mengweijin.tester.cucumber.util.TestCaseExcelUtils;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.entity.TestStep;
import com.mengweijin.tester.system.mapper.TestStepMapper;
import com.mengweijin.tester.system.service.TestStepService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * test step 服务实现类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
@Service
public class TestStepServiceImpl extends ServiceImpl<TestStepMapper, TestStep> implements TestStepService {

    @Autowired
    private TestStepMapper testStepMapper;

    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public void importStepFromExcel(TestCase testCase, List<TestStepSheet> testStepSheetList) {
        List<TestStepSheet> testStepSheets =
                TestCaseExcelUtils.filterTestStepSheetByCaseCode(testCase.getCode(), testStepSheetList);

        if (CollectionUtil.isNotEmpty(testStepSheets)) {
            for (TestStepSheet testStepSheet : testStepSheets) {
                TestStep testStep = mapperFacade.map(testStepSheet, TestStep.class);
                testStep.setCaseId(testCase.getId());
                this.save(testStep);
            }
        }
    }

    @Override
    public List<TestStepSheet> getTestStepSheetByApiId(Long apiId) {
        List<TestStep> testStepList = testStepMapper.getTestStepByApiId(apiId);
        return mapperFacade.mapAsList(testStepList, TestStepSheet.class);
    }
}

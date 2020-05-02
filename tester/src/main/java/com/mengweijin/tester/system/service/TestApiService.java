package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.cucumber.entity.TestCaseExcel;
import com.mengweijin.tester.system.entity.TestApi;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * test case 服务类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
public interface TestApiService extends IService<TestApi> {

    /**
     * run all api test case
     * @param apiId apiId
     */
    void runApiCase(Long apiId);

    /**
     * Import test case from excel
     * @param apiId apiId
     * @param fileList file List
     */
    void importCaseFromExcel(Long apiId, List<File> fileList);

    /**
     * Get test case excel
     * @param apiId apiId
     * @return TestCaseExcel
     */
    TestCaseExcel getTestCaseExcel(Long apiId);

    /**
     * select TestApi page VO
     * @param page page
     * @param testApi testApi
     * @return map
     */
    IPage<Map<String, Object>> selectPageVO(IPage<Map<String, Object>> page, TestApi testApi);
}

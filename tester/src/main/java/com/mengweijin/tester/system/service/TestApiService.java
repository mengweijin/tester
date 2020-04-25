package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.cucumber.entity.TestCaseExcel;
import com.mengweijin.tester.system.entity.TestApi;

import java.io.File;
import java.util.List;

/**
 * <p>
 * test case 服务类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
public interface TestApiService extends IService<TestApi> {

    void runApiCase(Long apiId);

    void importCaseFromExcel(Long apiId, List<File> fileList);

    TestCaseExcel getTestCaseExcel(Long apiId);
}

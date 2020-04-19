package com.mengweijin.tester.system.service;

import com.mengweijin.tester.system.entity.TestApi;
import com.baomidou.mybatisplus.extension.service.IService;

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
}

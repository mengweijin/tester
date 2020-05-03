package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.system.entity.TestProject;

import java.util.Map;

/**
 * <p>
 * test case 服务类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
public interface TestProjectService extends IService<TestProject> {

    /**
     * select TestProject VO
     * @param page page
     * @param testProject testProject
     * @return map
     */
    IPage<Map<String, Object>> selectPageVO(IPage<Map<String, Object>> page, TestProject testProject);
}

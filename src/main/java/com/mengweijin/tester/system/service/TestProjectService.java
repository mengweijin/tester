package com.mengweijin.tester.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mengweijin.tester.system.entity.TestProject;
import com.mengweijin.tester.system.vo.TestProjectVO;

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
     * @return TestProjectVO
     */
    IPage<TestProjectVO> selectPageVO(IPage<TestProject> page, TestProject testProject);
}

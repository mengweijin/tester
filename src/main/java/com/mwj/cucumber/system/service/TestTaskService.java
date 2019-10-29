package com.mwj.cucumber.system.service;

import com.mwj.cucumber.framework.web.service.BaseService;
import com.mwj.cucumber.system.entity.TestTask;
import com.mwj.cucumber.system.repository.TestTaskRepository;

/**
 * @author Meng Wei Jin
 * @description
 * @date Create in 2019-07-28 0:36
 **/
public interface TestTaskService extends BaseService<TestTask, Long, TestTaskRepository> {

}

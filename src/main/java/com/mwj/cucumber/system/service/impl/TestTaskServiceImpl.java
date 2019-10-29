package com.mwj.cucumber.system.service.impl;

import com.mwj.cucumber.framework.web.service.BaseServiceImpl;
import com.mwj.cucumber.system.entity.TestTask;
import com.mwj.cucumber.system.repository.TestTaskRepository;
import com.mwj.cucumber.system.service.TestTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-29 21:48
 **/
@Service
public class TestTaskServiceImpl extends BaseServiceImpl<TestTask, Long, TestTaskRepository> implements TestTaskService {

    @Autowired
    private TestTaskRepository testTaskRepository;

}

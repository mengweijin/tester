package com.mwj.tester.system.service.impl;

import com.mwj.tester.framework.web.service.BaseServiceImpl;
import com.mwj.tester.system.entity.TestTask;
import com.mwj.tester.system.repository.TestTaskRepository;
import com.mwj.tester.system.service.TestTaskService;
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

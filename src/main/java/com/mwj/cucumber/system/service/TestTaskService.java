package com.mwj.cucumber.system.service;

import com.mwj.cucumber.system.repository.TestTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meng Wei Jin
 * @description
 * @date Create in 2019-07-28 0:36
 **/
@Service
public class TestTaskService {

    @Autowired
    private TestTaskRepository testTaskRepository;

}

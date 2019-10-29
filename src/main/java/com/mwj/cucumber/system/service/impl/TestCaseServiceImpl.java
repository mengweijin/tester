package com.mwj.cucumber.system.service.impl;

import com.mwj.cucumber.framework.web.service.BaseServiceImpl;
import com.mwj.cucumber.system.entity.TestCase;
import com.mwj.cucumber.system.repository.TestCaseRepository;
import com.mwj.cucumber.system.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-29 21:46
 **/
@Service
public class TestCaseServiceImpl extends BaseServiceImpl<TestCase, Long, TestCaseRepository> implements TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

}

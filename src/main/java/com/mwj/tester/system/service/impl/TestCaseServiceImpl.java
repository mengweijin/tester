package com.mwj.tester.system.service.impl;

import com.mwj.tester.framework.web.service.BaseServiceImpl;
import com.mwj.tester.system.entity.TestCase;
import com.mwj.tester.system.repository.TestCaseRepository;
import com.mwj.tester.system.service.TestCaseService;
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

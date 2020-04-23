package com.mengweijin.tester.system.controller;

import com.mengweijin.tester.system.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TestCase Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@RestController
@RequestMapping("/system/testCase")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @GetMapping("/run/{caseId}")
    public void runCase(@PathVariable Long caseId){
        testCaseService.runCase(caseId);
    }

}

package com.mengweijin.tester.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * TestCase Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Validated
@RestController
@RequestMapping("/system/test/case")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @GetMapping("/run/{caseId}")
    public void runCase(@PathVariable Long caseId) {
        testCaseService.runCase(caseId);
    }

    @GetMapping("/{id}")
    public TestCase getById(@Valid @Range @PathVariable("id") Long id) {
        return testCaseService.getById(id);
    }

    @GetMapping
    public IPage<TestCase> getPage(IPage<TestCase> page, @Valid TestCase testCase) {
        return testCaseService.page(page, new QueryWrapper<>(testCase));
    }

    @PostMapping
    public void add(@Valid @RequestBody TestCase testCase) {
        testCaseService.save(testCase);
    }

    @PutMapping
    public void update(@Valid @RequestBody TestCase testCase) {
        testCaseService.updateById(testCase);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @Range @PathVariable("id") Long id) {
        testCaseService.removeById(id);
    }
}

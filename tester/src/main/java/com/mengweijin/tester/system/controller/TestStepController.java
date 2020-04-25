package com.mengweijin.tester.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.TestStep;
import com.mengweijin.tester.system.service.TestStepService;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * TestStepAssert Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Validated
@RestController
@RequestMapping("/system/test/step")
public class TestStepController {

    @Autowired
    private TestStepService testStepService;

    @GetMapping("/{id}")
    public TestStep getById(@Valid @Range @PathVariable("id") Long id) {
        return testStepService.getById(id);
    }

    @GetMapping
    public IPage<TestStep> getPage(IPage<TestStep> page, @Valid TestStep testStep) {
        return testStepService.page(page, new QueryWrapper<>(testStep));
    }

    @PostMapping
    public void add(@Valid TestStep testStep) {
        testStepService.save(testStep);
    }

    @PutMapping
    public void update(@Valid TestStep testStep) {
        testStepService.updateById(testStep);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @Range @PathVariable("id") Long id) {
        testStepService.removeById(id);
    }
}

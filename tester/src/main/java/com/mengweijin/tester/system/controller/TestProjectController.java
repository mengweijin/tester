package com.mengweijin.tester.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.TestProject;
import com.mengweijin.tester.system.service.TestProjectService;
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
import java.util.List;
import java.util.Map;

/**
 * <p>
 * TestProject Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Validated
@RestController
@RequestMapping("/system/test/project")
public class TestProjectController {

    @Autowired
    private TestProjectService testProjectService;

    @GetMapping("/{id}")
    public TestProject getById(@Valid @Range @PathVariable("id") Long id) {
        return testProjectService.getById(id);
    }

    @GetMapping
    public IPage<Map<String, Object>> selectPageVO(IPage<Map<String, Object>> page, @Valid TestProject testProject) {
        return testProjectService.selectPageVO(page, testProject);
    }

    @PostMapping
    public void add(@Valid @RequestBody TestProject testProject) {
        testProjectService.save(testProject);
    }

    @PutMapping
    public void update(@Valid @RequestBody TestProject testProject) {
        testProjectService.updateById(testProject);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @Range @PathVariable("id") Long id) {
        testProjectService.removeById(id);
    }

    @GetMapping("/list")
    public List<TestProject> getProjectList() {
        return testProjectService.list();
    }

}

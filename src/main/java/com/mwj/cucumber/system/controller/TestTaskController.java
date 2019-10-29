package com.mwj.cucumber.system.controller;


import com.mwj.cucumber.framework.page.Pager;
import com.mwj.cucumber.framework.web.controller.BaseController;
import com.mwj.cucumber.system.entity.TestTask;
import com.mwj.cucumber.system.repository.TestTaskRepository;
import com.mwj.cucumber.system.service.TestTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Meng Wei Jin
 * @description
 * @date Create in 2019-07-27 17:27
 **/
@RestController
@RequestMapping("/task")
public class TestTaskController extends BaseController {

    @Autowired
    private TestTaskRepository testTaskRepository;

    @Autowired
    private TestTaskService testTaskService;

    @PostMapping("/insert")
    public void insert(){
        TestTask testTask = new TestTask();
        testTask.setName("task1");
        testTask.setDescription("description");
        testTaskRepository.save(testTask);
    }

    @PostMapping("/page")
    public Page findPage(Pager pager){
        Pageable pageable = PageRequest.of(pager.getPage(), pager.getLimit());
        return testTaskRepository.findAll(pageable);
    }
}

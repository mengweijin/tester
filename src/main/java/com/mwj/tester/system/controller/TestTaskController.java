package com.mwj.tester.system.controller;
import com.mwj.tester.framework.page.Pager;
import com.mwj.tester.framework.web.controller.BaseController;
import com.mwj.tester.system.entity.TestTask;
import com.mwj.tester.system.service.TestTaskService;
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
    private TestTaskService testTaskService;

    @PostMapping("/insert")
    public void insert(){
        TestTask testTask = new TestTask();
        testTask.setDatabaseEnvId(0L);
        testTask.setName("task1");
        testTask.setDescription("description");
        testTaskService.save(testTask);
    }

    @PostMapping("/page")
    public Page findPage(Pager pager){
        Pageable pageable = PageRequest.of(pager.getPage(), pager.getLimit());
        return testTaskService.findAll(pageable);
    }
}

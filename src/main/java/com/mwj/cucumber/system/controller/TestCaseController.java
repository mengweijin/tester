package com.mwj.cucumber.system.controller;
import com.mwj.cucumber.framework.page.Pager;
import com.mwj.cucumber.framework.web.controller.BaseController;
import com.mwj.cucumber.system.entity.TestCase;
import com.mwj.cucumber.system.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Meng Wei Jin
 * @description
 * @date Create in 2019-07-27 17:27
 **/
@RestController
@RequestMapping("/case")
public class TestCaseController extends BaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping("/insert")
    public void insert(){
        TestCase testCase = new TestCase();
        testCase.setUrl("http://localhost:80");
        testCase.setHttpMethod(HttpMethod.POST);
        testCase.setHeaders("header");
        testCase.setRequestBody("requestBody");
        testCase.setHttpStatus(200);
        testCase.setResponseBody("response body");
        testCase.setTestTaskId(1L);
        testCase.setDeleted(false);
        testCase.setEnabled(false);

        testCaseService.save(testCase);
    }

    @PostMapping("/page")
    public Page findPage(Pager pager){
        Pageable pageable = PageRequest.of(pager.getPage(), pager.getLimit());
        return testCaseService.findAll(pageable);
    }
}

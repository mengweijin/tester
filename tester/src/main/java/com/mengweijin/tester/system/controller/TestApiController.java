package com.mengweijin.tester.system.controller;

import com.mengweijin.tester.system.service.TestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * TestApi Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@RestController
@RequestMapping("/system/testApi")
public class TestApiController {

    @Autowired
    private TestApiService testApiService;

    @GetMapping("/{apiId}")
    public void runApiCase(@PathVariable Long apiId){
        testApiService.runApiCase(apiId);
    }

}

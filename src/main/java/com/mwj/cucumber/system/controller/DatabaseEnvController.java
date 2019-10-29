package com.mwj.cucumber.system.controller;

import com.mwj.cucumber.framework.page.Pager;
import com.mwj.cucumber.framework.web.controller.BaseController;
import com.mwj.cucumber.system.service.DatabaseEnvService;
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
@RequestMapping("/databaseEnv")
public class DatabaseEnvController extends BaseController {

    @Autowired
    private DatabaseEnvService databaseEnvService;

    @PostMapping("/page")
    public Page findPage(Pager pager){
        Pageable pageable = PageRequest.of(pager.getPage(), pager.getLimit());
        return databaseEnvService.findAll(pageable);
    }
}

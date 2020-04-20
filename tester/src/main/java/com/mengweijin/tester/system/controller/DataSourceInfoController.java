package com.mengweijin.tester.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.mengweijin.tester.system.service.DataSourceInfoService;

/**
 * <p>
 * DatasourceInfo Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@RestController
@RequestMapping("/system/datasourceInfo")
public class DataSourceInfoController {

    @Autowired
    private DataSourceInfoService datasourceInfoService;

}

package com.mengweijin.tester.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.DataSourceInfo;
import com.mengweijin.tester.system.service.DataSourceInfoService;
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
 * DataSourceInfo Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Validated
@RestController
@RequestMapping("/system/test/dataSource")
public class DataSourceInfoController {

    @Autowired
    private DataSourceInfoService dataSourceInfoService;

    @GetMapping("/{id}")
    public DataSourceInfo getById(@Valid @Range @PathVariable("id") Long id) {
        return dataSourceInfoService.getById(id);
    }

    @GetMapping
    public IPage<DataSourceInfo> getPage(IPage<DataSourceInfo> page, @Valid DataSourceInfo dataSourceInfo) {
        return dataSourceInfoService.page(page, new QueryWrapper<>(dataSourceInfo));
    }

    @PostMapping
    public void add(@Valid @RequestBody DataSourceInfo dataSourceInfo) {
        dataSourceInfoService.save(dataSourceInfo);
    }

    @PutMapping
    public void update(@Valid @RequestBody DataSourceInfo dataSourceInfo) {
        dataSourceInfoService.updateById(dataSourceInfo);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @Range @PathVariable("id") Long id) {
        dataSourceInfoService.removeById(id);
    }
}

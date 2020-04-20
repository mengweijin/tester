package com.mengweijin.tester.system;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.ds.pooled.PooledDSFactory;
import cn.hutool.setting.GroupedMap;
import cn.hutool.setting.Setting;
import com.mengweijin.tester.system.entity.DataSourceInfo;
import com.mengweijin.tester.system.service.DataSourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ApiDataSourceApplicationRunner implements ApplicationRunner {

    @Autowired
    private DataSourceInfoService datasourceInfoService;

    /**
     * init data source
     * get data source: DSFactory.get(dataSourceInfo.getKey());
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<DataSourceInfo> dataSourceInfoList = datasourceInfoService.list();
        Setting setting = new Setting();
        GroupedMap groupedMap = setting.getGroupedMap();
        dataSourceInfoList.forEach(dataSourceInfo -> {
            groupedMap.put(dataSourceInfo.getName(), "url", dataSourceInfo.getUrl());
            groupedMap.put(dataSourceInfo.getName(), "username", dataSourceInfo.getUsername());
            groupedMap.put(dataSourceInfo.getName(), "password", dataSourceInfo.getPassword());
            groupedMap.put(dataSourceInfo.getName(), "showSql", dataSourceInfo.getShowSql());
            groupedMap.put(dataSourceInfo.getName(), "formatSql", dataSourceInfo.getFormatSql());
            groupedMap.put(dataSourceInfo.getName(), "showParams", dataSourceInfo.getShowSql());
            groupedMap.put(dataSourceInfo.getName(), "sqlLevel", dataSourceInfo.getSqlLevel());
        });

        DSFactory.setCurrentDSFactory(new PooledDSFactory(setting));
    }
}

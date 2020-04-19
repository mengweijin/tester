package com.mengweijin.tester.system;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.db.ds.pooled.PooledDSFactory;
import cn.hutool.setting.GroupedMap;
import cn.hutool.setting.Setting;
import com.mengweijin.tester.system.entity.DatasourceInfo;
import com.mengweijin.tester.system.service.DatasourceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ApiDataSourceApplicationRunner implements ApplicationRunner {

    @Autowired
    private DatasourceInfoService datasourceInfoService;

    /**
     * init data source
     * get data source: DSFactory.get(dataSourceInfo.getKey());
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<DatasourceInfo> datasourceInfoList = datasourceInfoService.list();
        Setting setting = new Setting();
        GroupedMap groupedMap = setting.getGroupedMap();
        datasourceInfoList.forEach(datasourceInfo -> {
            groupedMap.put(datasourceInfo.getName(), "url", datasourceInfo.getUrl());
            groupedMap.put(datasourceInfo.getName(), "username", datasourceInfo.getUsername());
            groupedMap.put(datasourceInfo.getName(), "password", datasourceInfo.getPassword());
            groupedMap.put(datasourceInfo.getName(), "showSql", datasourceInfo.getShowSql());
            groupedMap.put(datasourceInfo.getName(), "formatSql", datasourceInfo.getFormatSql());
            groupedMap.put(datasourceInfo.getName(), "showParams", datasourceInfo.getShowSql());
            groupedMap.put(datasourceInfo.getName(), "sqlLevel", datasourceInfo.getSqlLevel());
        });

        DSFactory.setCurrentDSFactory(new PooledDSFactory(setting));
    }
}

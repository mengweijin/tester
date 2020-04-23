package com.mengweijin.tester.cucumber.step;

import cn.hutool.core.util.StrUtil;
import com.mengweijin.mwjwork.framework.constant.Const;
import com.mengweijin.mwjwork.framework.util.SpringUtils;
import com.mengweijin.tester.cucumber.CucumberService;
import com.mengweijin.tester.cucumber.entity.StepVariable;
import com.mengweijin.tester.cucumber.enums.ETag;
import com.mengweijin.tester.cucumber.util.ScenarioThreadLocal;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import io.cucumber.java8.En;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * database hooks
 * @author mengweijin
 */
public class DatabaseHooks implements En {

    public DatabaseHooks() {

        /**
         * init dataSource before each step when has @DataSource
         */
        Before(ETag.DATA_SOURCE.tag(), 1, (scenario) -> {
            StepVariable stepVariable = ScenarioThreadLocal.get();
            CucumberService cucumberService = SpringUtils.getBean(CucumberService.class);
            DataSource dataSource = cucumberService.getDataSource(stepVariable.getCaseId());
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            // 保存变量值
            stepVariable.setJdbcTemplate(jdbcTemplate);
        });

        /**
         * prepare data before each scenario when has @PrepareData
         */
        Before(ETag.PREPARE_DATA.tag(), 2, (scenario) -> {
            Long testCaseId = ScenarioThreadLocal.get().getCaseId();
            TestCaseService testCaseService = SpringUtils.getBean(TestCaseService.class);
            TestCase testCase = testCaseService.getById(testCaseId);
            String sqls = testCase.getPrepareDataSql();
            if (StrUtil.isNotBlank(sqls)) {
                String[] sqlArray = sqls.split(Const.SEMICOLON);
                JdbcTemplate jdbcTemplate = ScenarioThreadLocal.get().getJdbcTemplate();
                jdbcTemplate.batchUpdate(sqlArray);
            }
        });

    }

}

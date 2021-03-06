package com.mengweijin.tester.serenity.cucumber.step;

import cn.hutool.core.util.StrUtil;
import com.github.mengweijin.quickboot.framework.constant.Const;
import com.github.mengweijin.quickboot.framework.util.SpringUtils;
import com.mengweijin.tester.serenity.cucumber.entity.StepParameter;
import com.mengweijin.tester.serenity.cucumber.enums.ETag;
import com.mengweijin.tester.serenity.cucumber.service.CucumberService;
import com.mengweijin.tester.serenity.cucumber.util.ScenarioThreadLocal;
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
            StepParameter stepParameter = ScenarioThreadLocal.get();
            CucumberService cucumberService = SpringUtils.getBean(CucumberService.class);
            DataSource dataSource = cucumberService.getDataSource(stepParameter.getCaseId());
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            // 保存变量值
            stepParameter.setJdbcTemplate(jdbcTemplate);
        });

        /**
         * prepare data before each scenario when has @PrepareData
         */
        Before(ETag.PREPARE_DATA.tag(), 2, (scenario) -> {
            Long testCaseId = ScenarioThreadLocal.get().getCaseId();
            TestCaseService testCaseService = SpringUtils.getBean(TestCaseService.class);
            TestCase testCase = testCaseService.getById(testCaseId);
            String sqls = testCase.getPreparedDataSql();
            if (StrUtil.isNotBlank(sqls)) {
                String[] sqlArray = sqls.split(Const.SEMICOLON);
                JdbcTemplate jdbcTemplate = ScenarioThreadLocal.get().getJdbcTemplate();
                jdbcTemplate.batchUpdate(sqlArray);
            }
        });

    }

}

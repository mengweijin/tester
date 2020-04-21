package com.mengweijin.tester.cucumber.step;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Session;
import com.mengweijin.mwjwork.framework.util.SpringUtils;
import com.mengweijin.tester.cucumber.ScenarioThreadLocal;
import com.mengweijin.tester.cucumber.enums.ETag;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import io.cucumber.java8.En;

/**
 * database hooks
 * @author mengweijin
 */
public class DatabaseHooks implements En {

    public DatabaseHooks() {

        /**
         * start transaction before each step when has @Transactional
         */
        Before(ETag.TRANSACTIONAL.tag(), 1, (scenario) -> {
            Session session = ScenarioThreadLocal.get().getSession();
            session.beginTransaction();
        });

        /**
         * delete data before each scenario when has @DeleteDate
         */
        Before(ETag.PREPARE_DATA.tag(), 2, (scenario) -> {
            Long testCaseId = ScenarioThreadLocal.get().getCaseId();
            TestCaseService testCaseService = SpringUtils.getBean(TestCaseService.class);
            TestCase testCase = testCaseService.getById(testCaseId);
            String dataSqls = testCase.getPrepareDataSql();
            if(StrUtil.isNotBlank(dataSqls)){
                String[] sqlArray = dataSqls.split(";");
                Session session = ScenarioThreadLocal.get().getSession();
                session.executeBatch(sqlArray);
            }
        });

        /**
         * rollback transaction after each scenario when has @Rollback
         */
        After(ETag.ROLLBACK.tag(), 1, (scenario) -> {
            Session session = ScenarioThreadLocal.get().getSession();
            session.rollback();
        });

        /**
         * commit transaction after each scenario when has @Commit
         */
        After(ETag.COMMIT.tag(), 2, (scenario) -> {
            Session session = ScenarioThreadLocal.get().getSession();
            session.commit();
        });

    }

}

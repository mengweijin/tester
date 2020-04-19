package com.mengweijin.tester.cucumber.hook;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Session;
import cn.hutool.db.ds.DSFactory;
import com.mengweijin.mwjwork.framework.constant.Const;
import com.mengweijin.tester.cucumber.enums.ETag;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.service.TestCaseService;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * database hooks
 * @author mengweijin
 */
@Component
public class DatabaseHooks implements En {

    private Session session;

    @Autowired
    private TestCaseService testCaseService;

    public DatabaseHooks() {

        /**
         * start transaction before each step when has @Transactional
         */
        Before(ETag.TRANSACTIONAL.tag(), 1, (scenario) -> {
            String testCaseId = getTagValue(scenario, ETag.TEST_CASE_ID);
            String datasourceName = testCaseService.selectDatasourceName(NumberUtil.binaryToLong(testCaseId));
            session = Session.create(DSFactory.get(datasourceName));
            session.beginTransaction();
        });

        /**
         * delete data before each scenario when has @DeleteDate
         */
        Before(ETag.PREPARE_DATA.tag(), 2, (scenario) -> {
            String testCaseId = getTagValue(scenario, ETag.TEST_CASE_ID);
            TestCase testCase = testCaseService.getById(testCaseId);
            String dataSqls = testCase.getPrepareDataSql();
            if(StrUtil.isNotBlank(dataSqls)){
                String[] sqlArray = dataSqls.split(";");
                session.executeBatch(sqlArray);
            }
        });

        /**
         * rollback transaction after each scenario when has @Rollback
         */
        After(ETag.ROLLBACK.tag(), 1, (scenario) -> {
            if(session != null) {
                session.rollback();
            }

        });

        /**
         * commit transaction after each scenario when has @Commit
         */
        After(ETag.COMMIT.tag(), 2, (scenario) -> {
            if(session != null) {
                session.commit();
            }
        });

        /**
         * clear sessionMap after each scenario when has @End
         */
        After(ETag.END.tag(), Integer.MAX_VALUE, (scenario) -> {
            session = null;
        });

    }

    private String getTagValue(Scenario scenario, ETag tag){
        Collection<String> sourceTagNames = scenario.getSourceTagNames();
        List<String> list = sourceTagNames.stream()
                .filter(value -> value.startsWith(tag.tag()))
                .collect(Collectors.toList());
        if(CollectionUtil.isNotEmpty(list)){
            String tagString = list.get(0);
            return tagString.substring(tagString.indexOf(Const.DASH + 1));
        }
        return null;
    }

}

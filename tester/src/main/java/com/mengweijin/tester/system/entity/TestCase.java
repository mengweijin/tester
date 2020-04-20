package com.mengweijin.tester.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mengweijin.mwjwork.mybatis.BaseEntity;
import com.mengweijin.tester.cucumber.enums.ECaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * test case
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("AT_TEST_CASE")
public class TestCase extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Test case code, Associate TEST STEP ASSERT when excel import
     */
    private String code;

    /**
     * test case name
     */
    private String name;

    /**
     * test case description
     */
    private String description;

    /**
     * AT_TEST_API id
     */
    private Long apiId;

    /**
     * prepare data SQLs
     */
    private String prepareDataSql;

    /**
     * request url
     */
    private String requestUrl;

    /**
     * HTTP request method: GET/POST/PUT/DELETE
     */
    private String requestMethod;

    /**
     * request parameters JSON string
     */
    private String requestParams;

    /**
     * ECaseStatus enum.
     */
    private ECaseStatus status;

    /**
     * test case failed message.
     */
    private String failedMessage;

}

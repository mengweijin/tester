package com.mengweijin.tester.system.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mengweijin.mwjwork.mybatis.BaseEntity;
import com.mengweijin.tester.cucumber.enums.ECaseStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

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
     * 自动生成主键
     * JsonSerialize：JavaScript 无法处理 Java 的长整型 Long 导致精度丢失，具体表现为主键最后两位永远为 0
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    protected Long id;

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
     * prepared data SQLs
     */
    private String preparedDataSql;

    /**
     * request url
     */
    private String requestUrl;

    /**
     * HTTP request method: GET/POST/PUT/DELETE
     */
    private HttpMethod httpMethod;

    /**
     * url parameters JSON string
     */
    private String urlParams;

    /**
     * request parameters JSON string
     */
    private String requestParams;

    /**
     * ECaseStatus enum.
     */
    private ECaseStatus status;

    /**
     * feature file
     */
    private String feature;

    /**
     * test case failed message.
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private String failedMessage;

}

package com.mengweijin.tester.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.mengweijin.quickboot.mybatis.BaseEntity;
import com.mengweijin.tester.serenity.cucumber.enums.EStep;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * test step
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("AT_TEST_STEP")
public class TestStep extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自动生成主键
     * JsonSerialize：JavaScript 无法处理 Java 的长整型 Long 导致精度丢失，具体表现为主键最后两位永远为 0
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    protected Long id;

    /**
     * AT_TEST_CASE id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long caseId;

    /**
     * EStep enum. 
     */
    private EStep step;

    /**
     * The execution order of steps, generated by the program.
     */
    private Integer defaultIndex;

    /**
     * 需要断言的键。可选如下：
     * GIVEN_TOKEN:                    keep empty.
     * GIVEN_HTTP_HEADER:              keep empty.
     * WHEN_CALL_API:                  keep empty.
     * ASSERT_HTTP_CODE:               keep empty.
     * THEN_ASSERT_RESPONSE_JSON_PATH: JSON path in response body.
     * ASSERT_RESPONSE:                keep empty.
     * ASSERT_DB_DATA:                 write assert query SQL. If include a date type, please format it in SQL.
     */
    private String assertKey;

    /**
     * 预期的结果。
     * GIVEN_TOKEN:                    keep empty.
     * GIVEN_HTTP_HEADER:              keep empty.
     * WHEN_CALL_API:                  keep empty.
     * ASSERT_HTTP_CODE:               200;400;404;等
     * THEN_ASSERT_RESPONSE_JSON_PATH: JSON path的 value.
     * ASSERT_RESPONSE:                完整的response body.
     * ASSERT_DB_DATA:                 []括起来的sql查询结果
     */
    private String expectValue;

    private String actualValue;
}

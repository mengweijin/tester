package com.mengweijin.tester.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mengweijin.mwjwork.mybatis.BaseEntity;
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
@TableName("AT_TEST_API")
public class TestApi extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 自动生成主键
     * JsonSerialize：JavaScript 无法处理 Java 的长整型 Long 导致精度丢失，具体表现为主键最后两位永远为 0
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    protected Long id;

    /**
     * test api url
     */
    private String url;

    /**
     * HTTP method: GET/POST/PUT/DELETE
     */
    private String httpMethod;

    /**
     * AT_DATASOURCE_INFO id
     */
    private Long datasourceId;

    /**
     * AT_TEST_PROJECT id
     */
    private Long projectId;

    /**
     * Enable database transaction rollback, （0 disable, auto commit; 1 enable, auto rollback）
     */
    private Boolean transactionRollback;

}

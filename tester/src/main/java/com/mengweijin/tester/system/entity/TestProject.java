package com.mengweijin.tester.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("AT_TEST_PROJECT")
public class TestProject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * test project name
     */
    private String name;

    /**
     * test project description
     */
    private String description;

}

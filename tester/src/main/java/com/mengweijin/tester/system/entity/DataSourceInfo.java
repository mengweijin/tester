package com.mengweijin.tester.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mengweijin.mwjwork.mybatis.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * Data source info
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("AT_DATASOURCE_INFO")
public class DataSourceInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identification of data source name
     */
    private String name;

    /**
     * jdbc url
     */
    private String url;

    /**
     * Database username
     */
    private String username;

    /**
     * Database password
     */
    private String password;

    /**
     * true/false
     */
    private String showSql;

    /**
     * true/false
     */
    private String formatSql;

    /**
     * true/false
     */
    private String showParams;

    /**
     * debug/info/error/warn
     */
    private String sqlLevel;

}

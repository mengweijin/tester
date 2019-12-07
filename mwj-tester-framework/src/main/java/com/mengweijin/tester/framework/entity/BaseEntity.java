package com.mengweijin.tester.framework.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mengweijin.tester.common.constant.DateConst;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author mengweijin
 */
@Data
public abstract class BaseEntity implements Serializable {

    @TableField("CREATE_BY")
    protected String createBy;

    @TableField("UPDATE_BY")
    protected String updateBy;

    @DateTimeFormat(pattern = DateConst.YYYY_MM_DD_HH_MM_SS)
    @JsonFormat(pattern = DateConst.YYYY_MM_DD_HH_MM_SS)
    @TableField("CREATE_TIME")
    protected LocalDateTime createTime;

    @DateTimeFormat(pattern = DateConst.YYYY_MM_DD_HH_MM_SS)
    @JsonFormat(pattern = DateConst.YYYY_MM_DD_HH_MM_SS)
    @TableField("UPDATE_TIME")
    protected LocalDateTime updateTime;

    @TableLogic
    @TableField("DELETED")
    protected String deleted;

    @TableField("ENABLED")
    protected String enabled;

    @Version
    @TableField("VERSION")
    protected Integer version;
}

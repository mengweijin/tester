package com.mwj.cucumber.framework.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mwj.cucumber.framework.util.DateFormatUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author Meng Wei Jin
 * @description
 * @date Create in 2019-07-28 15:18
 **/
@Data
@DynamicInsert
@SelectBeforeUpdate
@DynamicUpdate
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    private static final String ID_GENERATOR_NAME = "ID_GENERATOR";

    private static final String STRATEGY_TIMESTAMP = "com.mwj.cucumber.framework.jpa.TimestampIdGenerator";

    /**
     * 自动生成主键
     * JsonSerialize：JavaScript 无法处理 Java 的长整型 Long 导致精度丢失，具体表现为主键最后两位永远为 0
     */
    @Id
    @GenericGenerator(name = ID_GENERATOR_NAME, strategy = STRATEGY_TIMESTAMP)
    @GeneratedValue(generator = ID_GENERATOR_NAME)
    @JsonSerialize(using = ToStringSerializer.class)
    @Column(name = "ID")
    protected Long id;

    @CreatedBy
    @JsonIgnore
    @Column(name = "CREATE_BY")
    protected String createBy;

    @CreatedDate
    @DateTimeFormat(pattern = DateFormatUtil.YYYY_MM_DD_HH_MM_SS)
    @JsonFormat(pattern = DateFormatUtil.YYYY_MM_DD_HH_MM_SS)
    @Column(name = "CREATE_TIME")
    protected LocalDateTime createTime;

    @LastModifiedBy
    @JsonIgnore
    @Column(name = "UPDATE_BY")
    protected String updateBy;

    @LastModifiedDate
    @DateTimeFormat(pattern = DateFormatUtil.YYYY_MM_DD_HH_MM_SS)
    @JsonFormat(pattern = DateFormatUtil.YYYY_MM_DD_HH_MM_SS)
    @Column(name = "UPDATE_TIME")
    protected LocalDateTime updateTime;

    /**
     * 删除标记 --系统只做逻辑删除
     */
    @Column
    protected Boolean deleted = Boolean.FALSE;

    /**
     * 启用标记 --默认启用
     */
    @Column
    protected Boolean enabled = Boolean.TRUE;
}

package com.mwj.tester.system.entity;

import com.mwj.tester.framework.web.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-29 21:57
 **/
@Entity
@Table(name = "DATABASE_ENV")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DatabaseEnv extends BaseEntity {

    @Column(unique = true, nullable = false, length = 64)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private String driverClassName;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String username;

    @Column
    private String password;
}

package com.mwj.cucumber.system.entity;

import com.mwj.cucumber.framework.web.entity.BaseEntity;
import com.mwj.cucumber.system.enums.HttpMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-28 21:47
 **/
@Entity
@Table(name = "TEST_CASE")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestCase extends BaseEntity {

    @Column
    private String url;

    @Enumerated(value = EnumType.STRING)
    @Column
    private HttpMethod httpMethod;


}

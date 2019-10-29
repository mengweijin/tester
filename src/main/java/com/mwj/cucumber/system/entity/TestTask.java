package com.mwj.cucumber.system.entity;

import com.mwj.cucumber.framework.web.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-28 21:45
 **/

@Entity
@Table(name = "TEST_TASK")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestTask extends BaseEntity {

    @Column(length = 30, unique = true)
    private String name;

    @Column(length = 255)
    private String description;
}

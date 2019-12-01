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
 * @date Create in 2019-10-28 21:45
 **/

@Entity
@Table(name = "TEST_TASK")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TestTask extends BaseEntity {

    @Column(length = 64, unique = true, nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Long databaseEnvId;
}

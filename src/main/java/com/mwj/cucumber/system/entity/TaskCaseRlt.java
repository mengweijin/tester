package com.mwj.cucumber.system.entity;

import com.mwj.cucumber.framework.web.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Meng Wei Jin
 * @date Create in 2019-10-28 21:48
 **/
@Entity
@Table(name = "TEST_TASK_CASE_RLT")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskCaseRlt extends BaseEntity {

    private Long taskId;

    private Long caseId;
}

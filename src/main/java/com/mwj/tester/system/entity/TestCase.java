package com.mwj.tester.system.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mwj.tester.framework.web.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.http.HttpMethod;

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

    @Column(nullable = false)
    private String url;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private HttpMethod httpMethod;

    /**
     * header json
     */
    @Column
    private String headers;

    /**
     * requestBody json
     */
    @Column
    private String requestBody;

    /**
     * expect http status code
     */
    @Column
    private Integer httpStatus;

    /**
     * expect response body
     */
    @Column
    private String responseBody;

    @JsonSerialize(using = ToStringSerializer.class)
    @Column(nullable = false)
    private Long testTaskId;
}

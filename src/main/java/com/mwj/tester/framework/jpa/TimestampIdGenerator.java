package com.mwj.tester.framework.jpa;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Meng Wei Jin
 * @description 自定义ID生成规则
 * @date Create in 2019-07-27 22:32
 **/
public class TimestampIdGenerator implements IdentifierGenerator {

    private static final String TIMESTAMP_ID = "TIMESTAMP_ID";

    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        return Long.valueOf(timestampId());
    }

    public static String timestampId() {
        synchronized(TIMESTAMP_ID){
            return DateFormatUtils.format(new Date(), YYYYMMDDHHMMSSSSS);
        }

    }
}

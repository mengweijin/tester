package com.mengweijin.tester.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengweijin.tester.system.entity.TestCase;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * test case Mapper 接口
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18
 */
 @Mapper
public interface TestCaseMapper extends BaseMapper<TestCase> {

    String selectDataSourceName(Long caseId);
}

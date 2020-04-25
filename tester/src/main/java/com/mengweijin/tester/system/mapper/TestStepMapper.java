package com.mengweijin.tester.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengweijin.tester.system.entity.TestStep;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * test step Mapper 接口
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18
 */
@Mapper
public interface TestStepMapper extends BaseMapper<TestStep> {

    List<TestStep> getTestStepByApiId(Long apiId);
}

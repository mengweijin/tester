package com.mengweijin.tester.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.TestProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * test case Mapper 接口
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18
 */
@Mapper
public interface TestProjectMapper extends BaseMapper<TestProject> {

    /**
     * select TestProject VO
     *
     * @param page        page
     * @param testProject testProject
     * @return map
     */
    IPage<Map<String, Object>> selectPageVO(IPage<Map<String, Object>> page, @Param("testProject") TestProject testProject);
}

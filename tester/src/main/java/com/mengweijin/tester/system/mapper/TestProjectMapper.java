package com.mengweijin.tester.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.TestProject;
import com.mengweijin.tester.system.vo.TestProjectVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @return TestProjectVO
     */
    IPage<TestProjectVO> selectPageVO(IPage<TestProject> page, @Param("testProject") TestProject testProject);
}

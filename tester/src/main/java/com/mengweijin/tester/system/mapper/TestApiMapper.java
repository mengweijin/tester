package com.mengweijin.tester.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.TestApi;
import com.mengweijin.tester.system.vo.TestApiVO;
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
public interface TestApiMapper extends BaseMapper<TestApi> {

    /**
     * 自定义分页查询
     * @param page IPage
     * @param testApi testApi
     * @return
     */
    IPage<TestApiVO> selectPageVO(IPage<TestApi> page, @Param("testApi") TestApi testApi);
}

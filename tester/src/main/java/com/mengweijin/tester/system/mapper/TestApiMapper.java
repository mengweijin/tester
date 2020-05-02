package com.mengweijin.tester.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengweijin.tester.system.entity.TestApi;
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
public interface TestApiMapper extends BaseMapper<TestApi> {

    /**
     * 自定义分页查询
     * @param page IPage
     * @param testApi testApi
     * @return
     */
    IPage<Map<String, Object>> selectPageVO(IPage<Map<String, Object>> page, @Param("testApi") TestApi testApi);
}

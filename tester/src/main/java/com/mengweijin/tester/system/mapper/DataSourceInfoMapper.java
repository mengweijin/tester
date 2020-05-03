package com.mengweijin.tester.system.mapper;

import com.mengweijin.tester.system.entity.DataSourceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Data source info Mapper 接口
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18
 */
 @Mapper
public interface DataSourceInfoMapper extends BaseMapper<DataSourceInfo> {

    /**
     * Get DataSourceInfo by case id
     * @param caseId case id
     * @return DataSourceInfo
     */
    DataSourceInfo getDataSourceInfoByCaseId(Long caseId);
}

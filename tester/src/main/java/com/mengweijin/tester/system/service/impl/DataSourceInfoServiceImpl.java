package com.mengweijin.tester.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.tester.system.entity.DataSourceInfo;
import com.mengweijin.tester.system.mapper.DataSourceInfoMapper;
import com.mengweijin.tester.system.service.DataSourceInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Data source info 服务实现类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DataSourceInfoServiceImpl extends ServiceImpl<DataSourceInfoMapper, DataSourceInfo> implements DataSourceInfoService {

}

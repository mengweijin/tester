package com.mengweijin.tester.cms.mapper;

import com.mengweijin.tester.cms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2019-12-07
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

package com.mengweijin.tester.cms.service.impl;

import com.mengweijin.tester.cms.entity.User;
import com.mengweijin.tester.cms.mapper.UserMapper;
import com.mengweijin.tester.cms.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2019-12-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

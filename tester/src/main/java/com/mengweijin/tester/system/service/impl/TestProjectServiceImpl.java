package com.mengweijin.tester.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.tester.system.entity.TestProject;
import com.mengweijin.tester.system.mapper.TestProjectMapper;
import com.mengweijin.tester.system.service.TestProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * test case 服务实现类
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2020-04-18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TestProjectServiceImpl extends ServiceImpl<TestProjectMapper, TestProject> implements TestProjectService {

}

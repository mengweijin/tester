package com.mengweijin.tester;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengweijin.generator.CodeGenerator;
import com.mengweijin.generator.ConfigProperty;
import com.mengweijin.mwjwork.framework.web.BaseController;
import com.mengweijin.mwjwork.mybatis.BaseEntity;
import org.junit.jupiter.api.Test;

public class CodeGeneratorTest {

    @Test
    void codeGenerator() {
        ConfigProperty config = new ConfigProperty();
        config.setAuthor("Meng Wei Jin");
        config.setTemplateLocation("templates/mybatis/");
        config.setPackagePath("com.mengweijin.aaaaaaaaaaaaaaaaaaaaaaaaa");
        config.setTables(new String[]{"AT_TEST_API"});
        config.setTablePrefix("");
        config.setSuperEntityClass(BaseEntity.class);
        config.setSuperDaoClass(BaseMapper.class);
        config.setSuperServiceClass(IService.class);
        config.setSuperServiceImplClass(ServiceImpl.class);
        config.setSuperControllerClass(BaseController.class);
        config.setSuperEntityColumns(new String[]{"CREATE_BY", "CREATE_TIME", "UPDATE_BY", "UPDATE_TIME", "DELETED"});
        config.setDbUrl("jdbc:mysql://192.168.233.155:3306/tester?useUnicode=true&useSSL=false&characterEncoding=utf8");
        config.setDbDriverName("com.mysql.cj.jdbc.Driver");
        config.setDbUsername("root");
        config.setDbPassword("root");
        new CodeGenerator(config).run();
    }
}

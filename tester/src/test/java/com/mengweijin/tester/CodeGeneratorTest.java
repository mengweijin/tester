package com.mengweijin.tester;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mengweijin.generator.CodeGenerator;
import com.github.mengweijin.generator.ConfigProperty;
import com.github.mengweijin.generator.EDefaultTemplatePath;
import com.mengweijin.mwjwork.framework.web.BaseController;
import com.mengweijin.mwjwork.mybatis.BaseEntity;
import org.junit.jupiter.api.Test;

public class CodeGeneratorTest {

    @Test
    void codeGenerator() {
        ConfigProperty config = new ConfigProperty();
        config.setAuthor("Meng Wei Jin");
        config.setTemplateLocation(EDefaultTemplatePath.MYBATIS.getTemplatePath());
        config.setPackagePath("com.mengweijin.aaaaaaaaaaaaaaaaaaaaaaaaa");
        config.setTables(new String[]{"AT_TEST_PROJECT"});
        config.setTablePrefix("AT_");
        config.setSuperEntityClass(BaseEntity.class);
        config.setSuperDaoClass(BaseMapper.class);
        config.setSuperServiceClass(IService.class);
        config.setSuperServiceImplClass(ServiceImpl.class);
        config.setSuperControllerClass(BaseController.class);
        config.setSuperEntityColumns(new String[]{"CREATE_BY", "CREATE_TIME", "UPDATE_BY", "UPDATE_TIME", "DELETED"});
        config.setDbUrl("jdbc:h2:file:D:/Source/Gitee/mwj-tester/h2/test;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL");
        config.setDbDriverName("org.h2.Driver");
        config.setDbUsername("sa");
        config.setDbPassword("");
        new CodeGenerator(config).run();
    }
}

package com.mwj.tester.system.service.impl;

import com.mwj.tester.framework.web.service.BaseServiceImpl;
import com.mwj.tester.system.entity.DatabaseEnv;
import com.mwj.tester.system.repository.DatabaseEnvRepository;
import com.mwj.tester.system.service.DatabaseEnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-29 21:46
 **/
@Service
public class DatabaseEnvServiceImpl extends BaseServiceImpl<DatabaseEnv, Long, DatabaseEnvRepository> implements DatabaseEnvService {

    @Autowired
    private DatabaseEnvRepository databaseEnvRepository;

}

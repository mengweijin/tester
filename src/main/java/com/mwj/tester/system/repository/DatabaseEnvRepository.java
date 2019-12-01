package com.mwj.tester.system.repository;

import com.mwj.tester.framework.jpa.BaseRepository;
import com.mwj.tester.system.entity.DatabaseEnv;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseEnvRepository extends BaseRepository<DatabaseEnv, Long> {

}

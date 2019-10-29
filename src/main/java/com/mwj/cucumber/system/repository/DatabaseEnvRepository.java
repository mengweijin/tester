package com.mwj.cucumber.system.repository;

import com.mwj.cucumber.framework.jpa.BaseRepository;
import com.mwj.cucumber.system.entity.DatabaseEnv;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseEnvRepository extends BaseRepository<DatabaseEnv, Long> {

}

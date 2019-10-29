package com.mwj.cucumber.system.repository;

import com.mwj.cucumber.framework.jpa.BaseRepository;
import com.mwj.cucumber.system.entity.TestTask;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTaskRepository extends BaseRepository<TestTask, Long> {

    TestTask findByName(String name);

}

package com.mwj.tester.system.repository;

import com.mwj.tester.framework.jpa.BaseRepository;
import com.mwj.tester.system.entity.TestTask;
import org.springframework.stereotype.Repository;

@Repository
public interface TestTaskRepository extends BaseRepository<TestTask, Long> {

    TestTask findByName(String name);

}

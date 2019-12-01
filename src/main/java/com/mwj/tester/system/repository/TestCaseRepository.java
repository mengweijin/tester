package com.mwj.tester.system.repository;

import com.mwj.tester.framework.jpa.BaseRepository;
import com.mwj.tester.system.entity.TestCase;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends BaseRepository<TestCase, Long> {

}

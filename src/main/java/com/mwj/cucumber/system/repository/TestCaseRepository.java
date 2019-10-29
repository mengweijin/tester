package com.mwj.cucumber.system.repository;

import com.mwj.cucumber.framework.jpa.BaseRepository;
import com.mwj.cucumber.system.entity.TestCase;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends BaseRepository<TestCase, Long> {

}

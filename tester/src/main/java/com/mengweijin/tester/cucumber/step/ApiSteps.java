package com.mengweijin.tester.cucumber.step;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jayway.jsonpath.JsonPath;
import com.mengweijin.mwjwork.framework.constant.Const;
import com.mengweijin.mwjwork.framework.util.SpringUtils;
import com.mengweijin.mwjwork.mybatis.sping.jdbc.CamelColumnMapRowMapper;
import com.mengweijin.tester.cucumber.entity.StepVariable;
import com.mengweijin.tester.cucumber.enums.EStep;
import com.mengweijin.tester.cucumber.util.ScenarioThreadLocal;
import com.mengweijin.tester.system.entity.TestCase;
import com.mengweijin.tester.system.entity.TestStep;
import com.mengweijin.tester.system.service.TestCaseService;
import com.mengweijin.tester.system.service.TestStepService;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Meng Wei Jin
 * @date Create in 2019-10-26 15:19
 **/
@Slf4j
public class ApiSteps implements En {

    public ApiSteps() {

        Given(EStep.GIVEN_TOKEN.getDescription() + " {long} {long}", (Long testCaseId, Long testStepId) -> {
            StepVariable stepVariable = ScenarioThreadLocal.get();
            stepVariable.setToken("abc");
        });

        Given(EStep.GIVEN_HTTP_HEADER.getDescription() + " {long} {long}", (Long testCaseId, Long testStepId) -> {
            String token = ScenarioThreadLocal.get().getToken();
            Assert.assertEquals("abc", token);
        });

        When(EStep.WHEN_CALL_API.getDescription() + " {long} {long}", (Long testCaseId, Long testStepId) -> {
            RestTemplate restTemplate = SpringUtils.getBean(RestTemplate.class);
            TestCaseService testCaseService = SpringUtils.getBean(TestCaseService.class);
            TestCase testCase = testCaseService.getById(testCaseId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> httpEntity = new HttpEntity<>(testCase.getRequestParams(), headers);
            Map<String, ?> uriVariables = new HashMap<>();
            if (StrUtil.isNotBlank(testCase.getUrlParams())) {
                uriVariables = new JSONObject(testCase.getUrlParams()).toMap();
            }

            ResponseEntity<Object> responseEntity;
            try {
                responseEntity = restTemplate.exchange(
                        testCase.getRequestUrl(),
                        testCase.getHttpMethod(),
                        httpEntity,
                        Object.class,
                        uriVariables);
            } catch (HttpStatusCodeException e) {
                responseEntity = ResponseEntity
                        .status(e.getRawStatusCode())
                        .headers(e.getResponseHeaders())
                        .body(e.getResponseBodyAsString());
            }

            StepVariable stepVariable = ScenarioThreadLocal.get();
            stepVariable.setResponseEntity(responseEntity);

        });

        Then(EStep.THEN_ASSERT_HTTP_CODE.getDescription() + " {long} {long}", (Long testCaseId, Long testStepId) -> {
            ResponseEntity<Object> responseEntity = ScenarioThreadLocal.get().getResponseEntity();
            TestStepService testStepService = SpringUtils.getBean(TestStepService.class);
            Assert.assertEquals(testStepService.getById(testStepId).getExpectValue(), String.valueOf(responseEntity.getStatusCode().value()));
        });

        Then(EStep.THEN_ASSERT_RESPONSE.getDescription() + " {long} {long}", (Long testCaseId, Long testStepId) -> {
            TestStepService testStepService = SpringUtils.getBean(TestStepService.class);
            ResponseEntity<Object> responseEntity = ScenarioThreadLocal.get().getResponseEntity();
            String expectValue = testStepService.getById(testStepId).getExpectValue();
            Object body = responseEntity.getBody();
            log.debug("Expect value: {}", expectValue);
            log.debug("Actual value: {}", JSON.toJSONString(body));
            if (body instanceof Map) {
                JSONAssert.assertEquals(expectValue, new JSONObject(JSON.toJSONString(body)), false);
            } else if (body instanceof Collection) {
                JSONAssert.assertEquals(expectValue, new JSONArray(JSON.toJSONString(body)), false);
            } else {
                Assert.assertEquals(expectValue, String.valueOf(body));
            }

        });

        Then(EStep.THEN_ASSERT_RESPONSE_JSON_PATH.getDescription() + " {long} {long}", (Long testCaseId, Long testStepId) -> {
            TestStepService testStepService = SpringUtils.getBean(TestStepService.class);
            ResponseEntity<Object> responseEntity = ScenarioThreadLocal.get().getResponseEntity();
            TestStep testStep = testStepService.getById(testStepId);
            String[] assertKeys = testStep.getAssertKey().split(Const.SEMICOLON);
            String expectValue = testStep.getExpectValue();
            String responseValue = JSON.toJSONString(responseEntity.getBody());

            JSONObject jsonObject = new JSONObject();
            Arrays.stream(assertKeys).forEach(jsonPath -> {
                Object object = JsonPath.read(responseValue, jsonPath);
                jsonObject.put(jsonPath, object);
            });
            log.debug("Expect value: {}", expectValue);
            log.debug("Actual value: {}", jsonObject);
            JSONAssert.assertEquals(expectValue, jsonObject, false);

        });

        Then(EStep.THEN_ASSERT_DB_DATA.getDescription() + " {long} {long}", (Long testCaseId, Long testStepId) -> {
            JdbcTemplate jdbcTemplate = ScenarioThreadLocal.get().getJdbcTemplate();
            TestStepService testStepService = SpringUtils.getBean(TestStepService.class);
            TestStep testStep = testStepService.getById(testStepId);
            String assertKey = testStep.getAssertKey();
            cn.hutool.core.lang.Assert.notBlank(assertKey);
            String[] assertSQL = assertKey.split(Const.SEMICOLON);

            List<Map<String, Object>> mapList = jdbcTemplate.query(assertSQL[0], new CamelColumnMapRowMapper());
            String expectValue = testStep.getExpectValue();
            String actualValue = JSON.toJSONString(mapList,
                    SerializerFeature.WriteNonStringValueAsString,
                    SerializerFeature.WriteMapNullValue);

            log.debug("Expect value: {}", expectValue);
            log.debug("Actual value: {}", actualValue);
            JSONAssert.assertEquals(expectValue, new JSONArray(actualValue), false);
        });

    }
}

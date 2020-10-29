package com.mengweijin.tester.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.mengweijin.quickboot.framework.exception.ServerException;
import com.github.mengweijin.quickboot.framework.util.UploadUtils;
import com.mengweijin.tester.serenity.cucumber.entity.TestCaseExcel;
import com.mengweijin.tester.serenity.cucumber.util.TestCaseExcelUtils;
import com.mengweijin.tester.system.entity.TestApi;
import com.mengweijin.tester.system.service.TestApiService;
import com.mengweijin.tester.system.vo.TestApiVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 * TestApi Controller
 * </p>
 *
 * @author Meng Wei Jin
 * @date 2020-04-18 19:33:25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/system/test/api")
public class TestApiController {

    @Autowired
    private TestApiService testApiService;

    @GetMapping("/run/{apiId}")
    public void runApiCase(@PathVariable Long apiId) {
        testApiService.runApiCase(apiId);
    }

    @PostMapping("/import/{apiId}/case")
    public void importCaseFromExcel(@PathVariable Long apiId, HttpServletRequest request) {
        List<File> fileList = UploadUtils.uploadFile(request);
        testApiService.importCaseFromExcel(apiId, fileList);
    }

    @GetMapping("/export/{apiId}/case")
    public void exportCaseToExcel(@PathVariable Long apiId, HttpServletResponse response) {
        TestCaseExcel testCaseExcel = testApiService.getTestCaseExcel(apiId);
        Workbook workbook = TestCaseExcelUtils.exportTestCaseToExcel(testCaseExcel);
        try {
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=API_" + apiId + "_test_case.xlsx");
            workbook.write(response.getOutputStream());
        } catch (ClientAbortException e) {
            log.warn("User cancel download.");
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    @GetMapping("/{id}")
    public TestApi getById(@Valid @Range @PathVariable("id") Long id) {
        return testApiService.getById(id);
    }

    @GetMapping("/url/{url}")
    public List<TestApi> getByUrl(@Valid @NotBlank @PathVariable("url") String url) {
        return testApiService.lambdaQuery().like(TestApi::getUrl, url).list();
    }

    @GetMapping
    public IPage<TestApiVO> selectPageVO(IPage<TestApi> page, @Valid TestApi testApi) {
        return testApiService.selectPageVO(page, testApi);
    }

    @PostMapping
    public void add(@Valid @RequestBody TestApi testApi) {
        testApiService.save(testApi);
    }

    @PutMapping
    public void update(@Valid @RequestBody TestApi testApi) {
        testApiService.updateById(testApi);
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @Range @PathVariable("id") Long id) {
        testApiService.removeById(id);
    }

}

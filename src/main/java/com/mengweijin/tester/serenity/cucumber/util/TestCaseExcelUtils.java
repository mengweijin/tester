package com.mengweijin.tester.serenity.cucumber.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelStyleType;
import cn.hutool.core.lang.Assert;
import com.github.mengweijin.quickboot.framework.exception.ServerException;
import com.mengweijin.tester.serenity.cucumber.entity.TestCaseExcel;
import com.mengweijin.tester.serenity.cucumber.entity.TestCaseSheet;
import com.mengweijin.tester.serenity.cucumber.entity.TestStepSheet;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mengweijin
 */
@Slf4j
public class TestCaseExcelUtils {

    public static final String TEMPLATE = "test_case_template.xlsx";

    public static Workbook exportTestCaseToExcel(TestCaseExcel testCaseExcel) {
        TemplateExportParams params = new TemplateExportParams(TEMPLATE, 0, 1);
        // 标题开始行
        params.setHeadingStartRow(0);
        // 标题行数
        params.setHeadingRows(1);
        // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
        params.setStyle(ExcelStyleType.BORDER.getClazz());

        Map<String, Object> map = new HashMap<>(2);
        map.put("caseList", testCaseExcel.getTestCaseSheetList());
        map.put("stepList", testCaseExcel.getTestStepSheetList());
        return ExcelExportUtil.exportExcel(params, map);
    }

    /**
     * import test case excel
     *
     * @param file excel file
     * @return TestCaseExcel
     */
    public static TestCaseExcel importTestCaseFromExcel(File file) {
        TestCaseExcel testCaseExcel = new TestCaseExcel();
        try (InputStream testCaseIn = new FileInputStream(file);
             InputStream testStepIn = new FileInputStream(file)) {
            ImportParams params = new ImportParams();
            params.setTitleRows(0);
            params.setHeadRows(1);
            params.setStartSheetIndex(0);
            params.setSheetNum(1);
            params.setKeyIndex(0);
            List<TestCaseSheet> testCaseSheetList = ExcelImportUtil.importExcel(testCaseIn, TestCaseSheet.class, params);

            params.setStartSheetIndex(1);
            List<TestStepSheet> testStepSheetList = ExcelImportUtil.importExcel(testStepIn, TestStepSheet.class, params);

            testCaseExcel.setTestCaseSheetList(testCaseSheetList);
            testCaseExcel.setTestStepSheetList(testStepSheetList);
        } catch (FileNotFoundException e) {
            log.error("Throw a file not found exception when import test case excel!", e);
        } catch (Exception e) {
            log.error("Throw a import test case excel exception!", e);
            throw new ServerException(e);
        }

        return testCaseExcel;
    }

    public static List<TestStepSheet> filterTestStepSheetByCaseCode(String caseCode, List<TestStepSheet> testStepSheetList) {
        Assert.notNull(caseCode);
        Assert.notNull(testStepSheetList);
        List<TestStepSheet> collect = testStepSheetList.stream()
                .filter(testStepSheet -> caseCode.equals(testStepSheet.getCaseCode()))
                .collect(Collectors.toList());

        return collect;
    }
}

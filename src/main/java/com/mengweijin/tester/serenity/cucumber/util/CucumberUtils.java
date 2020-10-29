package com.mengweijin.tester.serenity.cucumber.util;

import cn.hutool.core.collection.CollectionUtil;
import com.github.mengweijin.quickboot.framework.constant.Const;

import java.io.File;
import java.util.List;

/**
 * @author mengweijin
 */
public class CucumberUtils {

    public static final String CUCUMBER_TMP_PATH = Const.JAVA_TMP_PATH + "cucumber" + File.separatorChar;

    public static final String CUCUMBER_FEATURE_TMP_PATH = CUCUMBER_TMP_PATH + "feature" + File.separatorChar;

    public static String buildFeatureContent(List<String> featureContentList) {
        StringBuilder builder = new StringBuilder();
        if (CollectionUtil.isNotEmpty(featureContentList)) {
            featureContentList.forEach(line -> builder.append(line).append("\r\n"));
        }
        return builder.toString();
    }
}

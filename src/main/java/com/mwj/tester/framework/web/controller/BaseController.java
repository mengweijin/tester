package com.mwj.tester.framework.web.controller;

import com.mwj.tester.framework.util.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * @author Meng Wei Jin
 * @description
 **/
@Slf4j
@Validated
@Transactional(rollbackFor = {Exception.class})
public class BaseController {

    /**
     * 页面重定向
     */
    public String redirect(String url) {
        return String.format("redirect:%s", url);
    }

    /**
     * 设置请求参数
     * @param key
     * @param value
     */
    public void setAttribute(String key, Object value){
        ServletUtils.getRequest().setAttribute(key, value);
    }

}

package com.mengweijin.tester.cms.controller;

import com.mengweijin.tester.cms.entity.User;
import com.mengweijin.tester.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Meng Wei Jin
 * @since 2019-12-07
 */
@RestController
@RequestMapping("/cms/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @ResponseBody
    public User getById(@PathVariable("username") String username){
        return userService.getById(username);
    }

}

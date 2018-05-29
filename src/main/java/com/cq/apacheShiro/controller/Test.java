package com.cq.apacheShiro.controller;

import com.cq.apacheShiro.model.User;
import com.cq.apacheShiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 13:21
 * @Description:
 */
@Controller
@RequestMapping("/test")
public class Test {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/test1")
    public User test() {
        return userService.findByUsername("admin");
    }
}

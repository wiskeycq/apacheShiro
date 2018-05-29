package com.cq.apacheShiro.service;

import com.cq.apacheShiro.model.User;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 11:26
 * @Description:
 */
public interface UserService {

    User findByUsername(String username);
}

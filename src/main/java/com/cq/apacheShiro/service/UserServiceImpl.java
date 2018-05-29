package com.cq.apacheShiro.service;

import com.cq.apacheShiro.mapper.UserMapper;
import com.cq.apacheShiro.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 11:28
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}

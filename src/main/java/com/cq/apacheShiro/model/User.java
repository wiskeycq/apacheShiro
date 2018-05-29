package com.cq.apacheShiro.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 11:09
 * @Description:
 */
@Data
public class User {

    private Integer uid;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();
}

package com.cq.apacheShiro.model;

import lombok.Data;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 11:09
 * @Description:
 */
@Data
public class Role {

    private Integer rid;

    private String rname;

    private Set<Permission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();
}

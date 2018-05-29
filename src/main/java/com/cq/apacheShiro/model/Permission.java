package com.cq.apacheShiro.model;

import lombok.Data;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 11:08
 * @Description: @Data注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 */
@Data
public class Permission {

    private Integer pid;

    private String name;

    private String url;
}

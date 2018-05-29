package com.cq.apacheShiro.mapper;

import com.cq.apacheShiro.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 11:24
 * @Description:
 */
public interface UserMapper {

    User findByUsername(@Param("username") String username);
}

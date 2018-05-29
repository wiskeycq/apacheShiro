package com.cq.apacheShiro;

import com.cq.apacheShiro.model.Permission;
import com.cq.apacheShiro.model.Role;
import com.cq.apacheShiro.model.User;
import com.cq.apacheShiro.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 13:20
 * @Description:
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权 登陆成功后，把user放在session中，再做授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从session中获取到用户
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if(CollectionUtils.isNotEmpty(roles)) {
            for (Role role : roles) {
                roleNameList.add(role.getRname());
                Set<Permission> permissions = role.getPermissions();
                if(CollectionUtils.isNotEmpty(permissions)) {
                    for (Permission permission : permissions) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.addRoles(roleNameList);
        return info;
    }

    //认证登陆
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username= usernamePasswordToken.getUsername();
        User user = userService.findByUsername(username);
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}

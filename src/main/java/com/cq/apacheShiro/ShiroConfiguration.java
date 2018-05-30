package com.cq.apacheShiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @Auther: caoqsq
 * @Date: 2018/5/29 14:40
 * @Description:
 */
@Configuration //Configuration 项目启动的时候自动配置这里面定义的类
public class ShiroConfiguration {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        shiroFilter.setLoginUrl("/login");
       // shiroFilter.setSuccessUrl("/index");
        shiroFilter.setUnauthorizedUrl("/unauthorized");

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/index", "authc");//其中DefaultFilter类中列出了所有shiro默认的拦截器
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/loginUser", "anon");
        //filterChainDefinitionMap.put("/admin","user");//user是判断当前有没登录，访问/admin路径，如果没有登录就跳转到登录页面，如果登录则跳转到/admin
        filterChainDefinitionMap.put("/admin", "roles[admin]");//访问/admin路径，如果没登录，则跳到登录页面。已登录得，如果这个用户有admin得角色，则跳到对应页面；如果没admin角色，则跳转到未授权页面。
        filterChainDefinitionMap.put("/edit","perms[edit]");//访问/edit路径时判断是不是有edit的权限，如果有则跳转，没有到未授权页面。
        filterChainDefinitionMap.put("/druid/**","anon");
        filterChainDefinitionMap.put("/**","user");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher credentialMatcher) {
        AuthRealm authRelam = new AuthRealm();
        authRelam.setCredentialsMatcher(credentialMatcher);
        return authRelam;
    }

    @Bean("credentialMatcher")
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    //shiro跟spring之间的关联
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}

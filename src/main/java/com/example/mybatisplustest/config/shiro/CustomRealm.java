package com.example.mybatisplustest.config.shiro;

import com.example.mybatisplustest.entity.Permissions;
import com.example.mybatisplustest.entity.Role;
import com.example.mybatisplustest.entity.User;
import com.example.mybatisplustest.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 * @author zhangjian
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;


    /**
     * 权限配置类 Authorization授权，将数据库中的角色和权限授权给输入的用户名
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //到数据库里查询要授权的内容
        User user = loginService.getUserByName(name);
        //记录用户的所有角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(Role role : user.getRoles()) {
            //将所有的角色信息添加进来。
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //将此次遍历到的角色的所有权限拿到，添加·进
            for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证配置类 用户身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //从token获取用户名,从主体传过来的认证信息中获取
        //加这一步的目的是在post请求时会先进入认证然后再到请求。
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        //获取用户的登录信息，用户名
        String name = authenticationToken.getPrincipal().toString();
        //根据service调用用户名，查找用户的全部信息
        //通过用户名到数据库获取凭证
        User user = loginService.getUserByName(name);
        if (null == user) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassWord(), getName());
            return simpleAuthenticationInfo;
        }

    }
}

package com.example.mybatisplustest.controller;

import com.example.mybatisplustest.common.ReturnData;
import com.example.mybatisplustest.entity.Permissions;
import com.example.mybatisplustest.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 89201
 */
@RestController
@Slf4j
public class LoginController {
    @GetMapping("/login")
    public ReturnData login(User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassWord())) {
            return ReturnData.fail("请输入用户名和密码！");
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassWord()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return ReturnData.fail("用户名不存在！");
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return ReturnData.fail("账号或密码错误！");
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return ReturnData.fail("没有权限！");
        }
        log.info("login success！");
        return ReturnData.success("login success!");
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public ReturnData admin() {
        return ReturnData.success("admin success!");
    }

    @RequiresPermissions("query")
    @GetMapping("/index")
    public ReturnData index() {
        return ReturnData.success("index success!");
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    public ReturnData add() {
        Map<String, Object> map = new HashMap<>();
        map.put("1",1);
        map.put("2","12345");
        map.put("3",new int[]{1,23,4});
        map.put("4",new ArrayList<String>().add("zhangjian"));
        map.put("5",new Permissions("123","jack"));
        return ReturnData.success(map);
    }
}

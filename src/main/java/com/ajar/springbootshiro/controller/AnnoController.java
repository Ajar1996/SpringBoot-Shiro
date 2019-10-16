package com.ajar.springbootshiro.controller;

import com.ajar.springbootshiro.enums.REnum;
import com.ajar.springbootshiro.utils.Assert;
import com.ajar.springbootshiro.utils.RUtil;
import com.ajar.springbootshiro.utils.ShiroUtil;
import com.ajar.springbootshiro.vo.Result;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: Ajar
 * @time: 2019/9/30 18:12
 */
@RestController
@RequestMapping("/anno")
public class AnnoController {

    /**
     * @description:登录
     * @author: Ajar
     * @time: 2019/10/11 12:32
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map) {
        Assert.isBlank(map.get("account"), "账号不能为空");
        Assert.isBlank(map.get("password"), "密码不能为空");
        try {
            Subject subject = ShiroUtil.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(map.get("account"), map.get("password"));
            subject.login(token);
        } catch (UnknownAccountException e) {
            return RUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(), REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        } catch (IncorrectCredentialsException e) {
            return RUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(), REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        } catch (DisabledAccountException e) {
            return RUtil.error(REnum.ACCOUNT_DISABLE.getCode(), REnum.ACCOUNT_DISABLE.getMessage());
        } catch (AuthenticationException e) {
            return RUtil.error(REnum.AUTH_ERROR.getCode(), REnum.AUTH_ERROR.getMessage());
        }
        return RUtil.success();
    }

    /**
     * @description:登出
     * @return: com.ajar.springbootshiro.vo.Result
     * @author: Ajar
     * @time: 2019/10/11 12:36
     */
    @PostMapping("/logout")
    public Result loginout(){
        ShiroUtil.logout();
        return RUtil.success();
    }

    /**
     * @description:未登录
     * @return: com.ajar.springbootshiro.vo.Result
     * @author: Ajar
     * @time: 2019/10/11 12:38
     */
    @PostMapping("/notLogin")
    public Result notLogin(){
        return RUtil.error(REnum.NOT_LOGIN.getCode(),REnum.NOT_LOGIN.getMessage());
    }



}
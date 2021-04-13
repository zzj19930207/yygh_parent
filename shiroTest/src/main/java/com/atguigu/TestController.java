package com.atguigu;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class TestController {
//    @Test
    public void testLogin(String path){
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
//        "classpath:shiro.ini"
        Factory<SecurityManager> factory=
                new IniSecurityManagerFactory(path);
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("wang","123");
        try{
            //4、登录，即身份验证
            subject.login(token);
        }catch(AuthenticationException e){
            System.out.println(e);
            //5、身份验证失败
        }
        Assert.assertEquals(true,subject.isAuthenticated()); //断言用户已经登录

    }

    @Test
    public void testHasRole(){
        String path = "classpath:shiro-role.ini";
        testLogin(path);
        boolean role1 = SecurityUtils.getSubject().hasRole("role2");
        System.out.println(role1);
    }

    @Test(expected = UnauthorizedException.class)
    public void testPermission(){
        String path = "classpath:shiro-permission.ini";
        testLogin(path);
        SecurityUtils.getSubject().checkPermissions("system:user:update,delete");

//        boolean permittedAll1 = SecurityUtils.getSubject().isPermittedAll("user:create", "user:update");
//        System.out.println("permittedAll1"+permittedAll1);
//        boolean[] permitted = SecurityUtils.getSubject().isPermitted("user:create","user:update");
//        boolean permittedAll = SecurityUtils.getSubject().isPermittedAll("user:create", "user:update");
//        System.out.println(permitted);
//        System.out.println(permittedAll);


    }

}

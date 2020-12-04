package com.example.mybatisplustest;

import cn.hutool.extra.mail.MailUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
@SpringBootTest
class MybatisPlusTestApplicationTests {
    Logger logger = LoggerFactory.getLogger(Logger.class);

    List<String> lists = new ArrayList<>();
    {
        lists.add("113.204.33.104");//no
        lists.add("113.204.33.105");
        lists.add("113.204.33.107");
        lists.add("113.204.33.108");//no
        lists.add("http://www.baidu.com");  //no
    }

    @Autowired
    private Environment env;

    @Value("${logging.file.path}")
    String path;

    @Test
    void t1(){
        int i = 51 / 50 +1;
        System.out.println(i);
    }

    @Test
    void testPropertiesPath(){
        System.out.println(path);
    }

    @Test
    void logTest(){
        logger.trace("这是trace日志========");
        logger.debug("这是debug日志========");
        logger.info("这是info日志========");
        logger.warn("这是warn日志========");
        logger.error("这是error日志========");
    }

    @Test
    void testPropertice(){
        System.out.println(env.getProperty("account.host"));
    }

    @Test
    void testProp(){
        List<String> lists = new ArrayList<>();
        lists.add("113.204.33.104");//no
        lists.add("113.204.33.105");
        System.out.println(lists.toString());
    }
    @Test
    void testMail(){

        MailUtil.send("892010796@qq.com", "测试", "邮件来自Hutool测试", false);
    }

    @Test
    void test(){
        lists.forEach(System.out::println);
    }

    @Test
    void contextLoads() {
//        List<User> users = userMapper.selectList(null);
//        Assert.assertEquals();
//        userMapper.
//        users.forEach(System.out::println);

//        userService.

        SimpleMailMessage message = new SimpleMailMessage();
        String ip = "113.204.33.108";//使用百度的ip，能ping通 113.204.33.102   www.baidu.com
        if (isConnect(ip)) {
            System.out.println("网络状态：" + "网络能ping通");
//            message.setFrom("892010796@qq.com");
//            message.setTo("892010796@qq.com");
//            message.setSubject("主题：简单邮件");
//            message.setText("测试邮件内容");
//            mailSender.send(message);
        } else {
            System.out.println("网络状态：" + "网络ping不通");
            message.setFrom("892010796@qq.com");
            message.setTo("892010796@qq.com");
            message.setSubject("主题：通知邮件");
            message.setText("你好，IP"+ ip + "出现异常，无法连接");
//            mailSender.send(message);
        }
    }
    public boolean isConnect(String ip) {
        InetAddress address;
        try {
            address = InetAddress.getByName(ip);
            return address.isReachable(500);
        } catch (IOException e) {
            return false;
        }
    }

    @Test
    void t2(){
        System.out.println(isConnect("260.70.15.246"));
    }

}

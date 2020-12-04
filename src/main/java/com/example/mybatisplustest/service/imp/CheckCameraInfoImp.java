package com.example.mybatisplustest.service.imp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplustest.mapper.CameraUserMapper;
import com.example.mybatisplustest.pojo.CameraUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CheckCameraInfoImp extends ServiceImpl<CameraUserMapper, CameraUser> {

    @Autowired
    private CameraUserMapper cameraUserMapper;

//    @Autowired
//    private CameraInfoMapper cameraInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${account.host}")
    String host;

    @Value("${account.port}")
    Integer port;

    @Value("${account.from}")
    String from;

    @Value("${account.user}")
    String user;

    @Value("${account.pass}")
    String pass;

    public void checkCameraNetWork(){
        System.out.println("测试中");
//获取配置文件中的账户配置(发件人邮箱配置)
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(port);
        account.setAuth(true);
        account.setFrom(from);
        account.setUser(user);
        account.setPass(pass);
        MailUtil.send(account, CollUtil.newArrayList("892010796@qq.com"), "异常IP通知邮件",  "测试", false);
    }

    public String checkCameraInfo(){
        ArrayList<String> errorIp = new ArrayList<>();
        errorIp.add("218.70.15.246");
        errorIp.add("222.178.53.66");

        //查询出所有异常IP对应的负责人信息
        List<CameraUser> cameraUsers = new ArrayList<>();
        for(String ip : errorIp){
            CameraUser cameraUser = cameraUserMapper.selectOne(new QueryWrapper<CameraUser>().eq("ip", ip));
            cameraUsers.add(cameraUser);
        }

        //保存IP---邮箱信息
        Map<String, String> ipAndEmail = new HashMap<>(16);
        //保存邮件信息 一个邮箱可对应多个异常IP
        Map<String, List<String>> infoMassage = new HashMap<>(16);

        for (CameraUser cameraUser : cameraUsers) {
            ipAndEmail.put(cameraUser.getIp(), cameraUser.getEmail());
        }

        for (Map.Entry<String, String> entry : ipAndEmail.entrySet()) {
            //redis不存在该IP，存入redis
            if (null != entry.getKey() && !stringRedisTemplate.hasKey(entry.getKey())) {
                if (infoMassage.containsKey(entry.getValue())) {
                    List<String> ips = infoMassage.get(entry.getValue());
                    ips.add(entry.getKey());
                    infoMassage.put(entry.getValue(), ips);
                } else {
                    ArrayList<String> ips = new ArrayList<>();
                    ips.add(entry.getKey());
                    infoMassage.put(entry.getValue(), ips);
                }
                stringRedisTemplate.opsForValue().set(entry.getKey(), entry.getKey(), 30, TimeUnit.SECONDS);
            }
        }

        System.out.println(infoMassage.toString());
        return null;
    }


    /**
     * 测试ip地址能否ping通
     * @param ip
     * @return 能true 不能 false
     */
    public boolean isConnect(String ip) {
        InetAddress address ;
        try {
            address = InetAddress.getByName(ip);
            return address.isReachable(500);
        } catch (IOException e) {
            return false;
        }
    }

}

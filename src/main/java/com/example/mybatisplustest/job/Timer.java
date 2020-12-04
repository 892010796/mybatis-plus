package com.example.mybatisplustest.job;

import com.example.mybatisplustest.service.imp.CheckCameraInfoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时器 每8分钟检查一次ip
 *
 * @author zhangjian
 */
@Component
public class Timer {

    @Autowired
    private CheckCameraInfoImp checkCameraInfoImp;
//    0 */8 * * * ?
//    0/5 * * * * ?
//    @Scheduled(cron = "0/5 * * * * ?")
    public void testTasks() {
        checkCameraInfoImp.checkCameraNetWork();
    }
}



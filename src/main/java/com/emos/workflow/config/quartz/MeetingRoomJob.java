package com.emos.workflow.config.quartz;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.emos.workflow.controller.form.ApprovalMeetingForm;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MeetingRoomJob extends QuartzJobBean {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public MeetingRoomJob() {
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        String meetingRoomId = jobDataMap.get("meetingRoomId").toString();
        DateTime expirationTime = DateUtil.parse(jobDataMap.get("expirationTime").toString(), "yyyy-MM-dd HH:mm:ss");  // Example date format

        long randomKey;
        do {
            randomKey = RandomUtil.randomLong(1L, 4294967295L);
        } while (redisTemplate.hasKey("meetingRoomKey" + randomKey));

        redisTemplate.opsForValue().set("meetingRoomKey" + randomKey, meetingRoomId);
        redisTemplate.expireAt("meetingRoomKey" + randomKey, expirationTime);

        redisTemplate.opsForValue().set(meetingRoomId, randomKey);
        redisTemplate.expireAt(meetingRoomId, expirationTime);
    }
}

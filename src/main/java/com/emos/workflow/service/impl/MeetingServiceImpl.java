package com.emos.workflow.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.emos.workflow.controller.form.ApprovalMeetingForm;
import com.emos.workflow.db.dao.TbMeetingDao;
import com.emos.workflow.exception.EmosException;
import com.emos.workflow.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private TbMeetingDao tbMeetingDao;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    public MeetingServiceImpl() {
    }

    @Override
    public List<String> searchUserMeetingInMonth(HashMap<String, Object> params) {
        return tbMeetingDao.searchUserMeetingInMonth(params);
    }

    @Override
    public Long searchRoomIdByUUID(String uuid) {
        return Long.parseLong(redisTemplate.opsForValue().get(uuid).toString());
    }

    @Override
    public HashMap<String, Object> searchMeetingByInstanceId(String instanceId) {
        HashMap meetingData = tbMeetingDao.searchMeetingByInstanceId(instanceId);
        if(meetingData == null) {
            return null;
        }
        String startDateStr = meetingData.get("date").toString();
        meetingData.put("date" , startDateStr);

        String startTimeStr = meetingData.get("start").toString();
        meetingData.put("start" , startTimeStr);
        DateTime startDateTime = DateUtil.parse(startDateStr + " " + startTimeStr , "yyyy-MM-dd HH:mm:ss");

        String endTimeStr = meetingData.get("end").toString();
        long hoursBetween = DateUtil.between(DateUtil.parse(startDateStr + " " + endTimeStr),
                startDateTime, DateUnit.HOUR, true);
        meetingData.put("end" , endTimeStr);
        meetingData.put("hours", hoursBetween);

        // Retrieving meeting members from the same department
        boolean sameDept = tbMeetingDao.searchMeetingMembersInSameDept(meetingData.get("uuid").toString());
        meetingData.put("sameDept", sameDept);

        return meetingData;
    }

    @Override
    public HashMap<String, Object> searchMeetingByUUID(String uuid) {
        return tbMeetingDao.searchMeetingByUUID(uuid);
    }

    @Override
    public void updateMeetingStatus(HashMap<String, Object> params) {
        if (tbMeetingDao.updateMeetingStatus(params) != 1) {
            throw new EmosException("出现异常");
        }
    }
}

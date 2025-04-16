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
        HashMap<String, Object> meetingData = tbMeetingDao.searchMeetingByInstanceId(instanceId);
//
//        // Parsing meeting start date and time
//        String startDateStr = (String) meetingData.get(ApprovalMeetingForm.ALLATORIxDEMO("]eMa"));
//        String startTimeStr = (String) meetingData.get(ApprovalMeetingForm.ALLATORIxDEMO("wMeKp"));
//        DateTime startDateTime = DateUtil.parse(startDateStr + ApprovalMeetingForm.ALLATORIxDEMO("$") + startTimeStr,
//                ApprovalMeetingForm.ALLATORIxDEMO("@}@}\u0014It)]`\u0019Lq>Ti"));
//
//        // Calculating the time difference between start and end time
//        String endTimeStr = (String) meetingData.get(ApprovalMeetingForm.ALLATORIxDEMO("aW`"));
//        long hoursBetween = DateUtil.between(DateUtil.parse(startDateStr + ApprovalMeetingForm.ALLATORIxDEMO("$") + endTimeStr,
//                ApprovalMeetingForm.ALLATORIxDEMO("@}@}\u0014It)]`\u0019Lq>Ti")),
//                startDateTime, DateUnit.HOUR, true);
//
//        meetingData.put(ApprovalMeetingForm.ALLATORIxDEMO("lVqKw"), hoursBetween);
//
//        // Retrieving meeting members from the same department
//        String departmentMembers = tbMeetingDao.searchMeetingMembersInSameDept(instanceId);
//        meetingData.put(ApprovalMeetingForm.ALLATORIxDEMO("JeTa}aIp"), departmentMembers);

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

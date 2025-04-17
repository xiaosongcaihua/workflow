package com.emos.workflow.config.quartz;

import java.util.HashMap;

import com.emos.workflow.service.MeetingService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class MeetingStatusJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(MeetingStatusJob.class);

    @Autowired
    private MeetingService meetingService;  // renamed from ALLATORIxDEMO

    public MeetingStatusJob() {
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        
        String meetingId = jobDataMap.get("meetingId").toString();
        Integer status = (Integer) jobDataMap.get("status");

        HashMap<String, Object> updateData = new HashMap<>();
        updateData.put("status", status);
        updateData.put("meetingId", meetingId);

        meetingService.updateMeetingStatus(updateData);

        logger.debug("Meeting status update completed for meetingId: {}", meetingId);
    }
}

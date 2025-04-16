package com.emos.workflow.config.quartz;

import com.emos.workflow.controller.form.ApprovalMeetingForm;
import java.util.Date;
import javax.annotation.Resource;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzUtil {

    private static final Logger logger = LoggerFactory.getLogger(QuartzUtil.class);
    
    @Resource
    private Scheduler scheduler;

    // Method to delete a job using its name and group
    public void deleteJob(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);

        try {
            // Resume the trigger, unschedule the job, and delete the job
            scheduler.resumeTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
            
            // Log the successful job deletion
            logger.debug("Job {} from group {} successfully deleted.", jobName, jobGroup);
        } catch (SchedulerException e) {
            // Log the error if something goes wrong
            logger.error("Error occurred while deleting job {} from group {}", jobName, jobGroup, e);
        }
    }

    // Method to add a new job with a specified start time
    public void addJob(JobDetail jobDetail, String jobName, String jobGroup, Date startTime) {
        try {
            // Create a trigger with a simple schedule and start time
            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .startAt(startTime)
                .build();
            
            // Schedule the job with the trigger
            scheduler.scheduleJob(jobDetail, trigger);
            
            // Log the successful job addition
            logger.debug("Job {} from group {} successfully scheduled to start at {}.", jobName, jobGroup, startTime);
        } catch (SchedulerException e) {
            // Log the error if something goes wrong
            logger.error("Error occurred while scheduling job {} from group {}.", jobName, jobGroup, e);
        }
    }

    // Default constructor
    public QuartzUtil() {
    }
}

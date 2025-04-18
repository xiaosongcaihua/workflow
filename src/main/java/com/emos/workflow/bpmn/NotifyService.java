package com.emos.workflow.bpmn;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.emos.workflow.config.quartz.MeetingRoomJob;
import com.emos.workflow.config.quartz.MeetingStatusJob;
import com.emos.workflow.config.quartz.QuartzUtil;
import com.emos.workflow.controller.form.DeleteProcessByIdForm;
import com.emos.workflow.service.MeetingService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotifyService implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(NotifyService.class);

    @Autowired
    private QuartzUtil quartzUtil;

    @Autowired
    private MeetingService meetingService;

    @Override
    public void execute(DelegateExecution execution) {
        // 获取流程变量
        Map<String, Object> variables = execution.getVariables();
        String uuid = variables.get("uuid").toString();  // uuid
        String callbackUrl = variables.get("url").toString(); // 回调地址

        // 查询会议信息
        HashMap<String, Object> meeting = meetingService.searchMeetingByUUID(uuid);
        System.out.println(meeting.get("end"));
        String date = meeting.get("date").toString();      // 日期
        String endTime = meeting.get("end").toString();   // 结束时间
        String startTime = meeting.get("start").toString();   // 开始时间
     // 更新状态为进行中
        HashMap<String , Object> map = new HashMap<>();
        map.put("status" , 3);
        map.put("uuid" , uuid);
        meetingService.updateMeetingStatus(map);

        // 预定会议室的定时任务
        JobDetail roomJob = JobBuilder.newJob(MeetingRoomJob.class).build();
        JobDataMap roomJobData = roomJob.getJobDataMap();
        roomJobData.put("uuid", uuid);
        DateTime startDateTime = DateUtil.parse(date + " " + startTime, "yyyy-MM-dd HH:mm");
        roomJobData.put("startTime", startDateTime);
        DateTime remindTime = DateUtil.parse(date + " " + startTime, "yyyy-MM-dd HH:mm")
                                      .offset(DateField.MINUTE, -15);
        quartzUtil.addJob(roomJob, uuid, "会议室定时任务", remindTime);

        // 删除上一个定时任务（比如审批超时处理）
//            quartzUtil.deleteJob(uuid, "审批超时任务");

        // 添加会议结束状态定时任务
        JobDetail endStatusJob = JobBuilder.newJob(MeetingStatusJob.class).build();
        JobDataMap endStatusMap = endStatusJob.getJobDataMap();
        endStatusMap.put("uuid", uuid);
        endStatusMap.put("status", 5);  // 结束状态码
        DateTime endTimeParsed = DateUtil.parse(date + " " + endTime, "yyyy-MM-dd HH:mm");
        quartzUtil.addJob(endStatusJob, uuid, "会议结束状态更新", endTimeParsed);

        // 添加会议开始状态定时任务
        JobDetail startStatusJob = JobBuilder.newJob(MeetingStatusJob.class).build();
        JobDataMap startStatusMap = startStatusJob.getJobDataMap();
        startStatusMap.put("uuid", uuid);
        startStatusMap.put("status", 4);  // 进行中状态码
        quartzUtil.addJob(startStatusJob, uuid, "会议开始状态更新", startDateTime);

        // 向流程通知地址发送回调
        JSONObject body = new JSONObject();
        body.set("status", "已完成");
        body.set("uuid", uuid);
        body.set("instanceId", execution.getProcessInstanceId());

        HttpResponse response = HttpRequest.post(callbackUrl)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .execute();

        log.debug(response.body());
    }
}

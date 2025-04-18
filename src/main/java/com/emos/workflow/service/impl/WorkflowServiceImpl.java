package com.emos.workflow.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.emos.workflow.config.quartz.MeetingWorkflowJob;
import com.emos.workflow.config.quartz.QuartzUtil;
import com.emos.workflow.controller.form.ApprovalMeetingForm;
import com.emos.workflow.controller.form.DeleteProcessByIdForm;
import com.emos.workflow.exception.EmosException;
import com.emos.workflow.service.MeetingService;
import com.emos.workflow.service.WorkflowService;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.Task;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.StreamInfo;
import org.springframework.stereotype.Service;

import java.util.*;

import org.activiti.engine.*;
@Service
public class WorkflowServiceImpl implements WorkflowService {
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private QuartzUtil quartzUtil;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ProcessEngine processEngine;

    // Check if the process is completed
    public boolean searchProcessStatus(String processInstanceId) {
        return runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult() == null;
    }

    // Approve meeting based on the approval form data
    public void approvalMeeting(HashMap<String, Object> formData) {
        String taskId = (String) formData.get("taskId");
        String approvalStatus = (String) formData.get("approval");

        taskService.setVariableLocal(taskId, "result",approvalStatus);
        taskService.complete(taskId);
    }

    // Get list of users who have completed the process
    public ArrayList<String> searchProcessUsers(String processInstanceId) {
        HistoricTaskInstanceQuery taskQuery = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).finished();
        ArrayList<String> users = new ArrayList<>();
        
        taskQuery.list().forEach(task -> users.add(task.getAssignee()));
        return users;
    }

    public String startMeetingProcess(HashMap formData) {
        String processInstanceId = runtimeService.startProcessInstanceByKey("meeting", formData).getProcessInstanceId();

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
                .taskAssignee((String) formData.get("creatorId"))
                .singleResult();

        String department = (String) formData.get("department");
        String roomId = (String) formData.get("uuid");
        String meetingTime = (String) formData.get("date");

        HashMap<String, Object> taskData = new HashMap<>();
        taskData.put("department", department);
        taskData.put("roomId", roomId);
        taskData.put("meetingTime", meetingTime);

        taskService.complete(task.getId(), taskData);

        // Schedule the job for the meeting workflow
        JobDetail jobDetail = JobBuilder.newJob(MeetingWorkflowJob.class).build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("department", department);
        jobDataMap.put("processInstanceId", processInstanceId);

        DateTime meetingDateTime = DateUtil.parse(meetingTime, "yyyy-MM-dd HH:mm:ss");
        quartzUtil.addJob(jobDetail, processInstanceId, "meeting", meetingDateTime);

        return processInstanceId;
    }

    // Delete a process instance by its ID
    public void deleteProcessById(String processInstanceId, String userId, String reason) {
        // Delete process instance if active
        if (runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).count() > 0) {
            runtimeService.deleteProcessInstance(processInstanceId, reason);
        }

        // Delete historical process instance
        if (historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).count() > 0) {
            historyService.deleteHistoricProcessInstance(processInstanceId);
        }

        // Delete associated jobs
        quartzUtil.deleteJob(processInstanceId, "meeting");
    }

    // Get list of tasks for a user, paginated
    public ArrayList<HashMap<String, Object>> searchUserTaskListByPage(HashMap<String, Object> paginationData) {
        int pageNumber = (Integer) paginationData.get("page");
        int pageSize = (Integer) paginationData.get("length");
        // type: 待审批 | 已审批
        String taskAssignee = (String) paginationData.get("type");

        int offset = (pageNumber - 1) * pageSize;
        ArrayList<HashMap<String, Object>> taskList = new ArrayList<>();

        if ("待审批".equals(taskAssignee)) {
            taskService.createTaskQuery().taskAssignee((String) paginationData.get("userId"))
                    .orderByTaskCreateTime().desc()
                    .listPage(offset, pageSize).forEach(task -> {
                        String processInstanceId = task.getProcessInstanceId();
                        //拿到任务的流程id
                        if (task.getProcessDefinitionId().contains("meeting")) {
                            HashMap<String, Object> taskData = meetingService.searchMeetingByInstanceId(processInstanceId);
                            if (taskData != null) {
                                taskData.put("taskId", task.getId());
                                taskData.put("processType" , "meeting");
                                taskList.add(taskData);
                            }
                        }
                    });
        } else if ("已审批".equals(taskAssignee)) {
            Set<String> set = new HashSet<>();
            historyService.createHistoricTaskInstanceQuery()
                    .includeTaskLocalVariables()
                    .includeProcessVariables()
                    .taskAssignee((String) paginationData.get("userId"))
                    .finished()
                    .orderByTaskCreateTime().desc()
                    .list().forEach(task -> {
                        if (!set.contains(task.getProcessInstanceId())){
                            set.add(task.getProcessInstanceId());
                        String processInstanceId = task.getProcessInstanceId();
                        //拿到任务的流程id
                        boolean processFinished = isProcessFinished(historyService, processInstanceId);
                        if (task.getProcessDefinitionId().contains("meeting")) {
                            HashMap<String, Object> taskData = meetingService.searchMeetingByInstanceId(processInstanceId);
                            if (taskData != null) {
                                if(task.getName().equals("总经理审批") || task.getName().equals("部门经理审批")){
                                    taskData.put("result_1" , task.getTaskLocalVariables().get("result"));
                                }
                                if(processFinished){
                                    setFinalAsgAndResult(historyService , processInstanceId , taskData , paginationData.get("token").toString());
                                } else {
                                    taskData.put("processStatus" , "未结束");
                                }
                                taskData.put("processType" , "meeting");
                                taskList.add(taskData);
                            }


                        }
                        }
                    });
        }

        return taskList;
    }
    public void setFinalAsgAndResult(HistoryService historyService, String processInstanceId , Map<String , Object> taskData , String token){
        taskData.put("processStatus" , "已结束");

        List<HistoricTaskInstance> taskList = historyService
                .createHistoricTaskInstanceQuery()
                .includeTaskLocalVariables()
                .includeProcessVariables()
                .processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceEndTime()
                .desc()
                .list();
        HistoricTaskInstance lastTask = taskList.get(0);
        String userId = lastTask.getAssignee();
        taskData.put("result_2" , lastTask.getTaskLocalVariables().get("result"));

        String url =  "http://localhost:8080/user/searchUserInfo";
        JSONObject json = new JSONObject();
        json.set("userId" , userId);
        Map<String ,String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("token" , token);
        HttpResponse resp = HttpRequest.post(url).headerMap(map , true)
                .body(json.toString()).execute();
        if (resp.getStatus() == 200) {
            json = JSONUtil.parseObj(resp.body());
            JSONObject json1 = JSONUtil.parseObj(json.get("result"));
            String name = json1.getStr("name");
            String photo = json1.getStr("photo");
            taskData.put("lastUserPhoto" , photo);
            taskData.put("lastUserName" , name);
        }
    }
    public boolean isProcessFinished(HistoryService historyService, String processInstanceId) {
        if (processInstanceId == null) return false;

        HistoricProcessInstance processInstance = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        return processInstance != null && processInstance.getEndTime() != null;
    }
}

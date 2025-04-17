package com.emos.workflow.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.emos.workflow.config.quartz.MeetingWorkflowJob;
import com.emos.workflow.config.quartz.QuartzUtil;
import com.emos.workflow.controller.form.ApprovalMeetingForm;
import com.emos.workflow.controller.form.DeleteProcessByIdForm;
import com.emos.workflow.service.MeetingService;
import com.emos.workflow.service.WorkflowService;

import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.Task;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
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
//        String processInstanceId = (String) formData.get(ApprovalMeetingForm.MEETING_ID);
//        String approvalStatus = (String) formData.get(DeleteProcessByIdForm.STATUS);
//
//        taskService.setVariableLocal(processInstanceId, ApprovalMeetingForm.APPROVAL_STATUS, approvalStatus);
//        taskService.complete(processInstanceId);
    }

    // Get list of users who have completed the process
    public ArrayList<String> searchProcessUsers(String processInstanceId) {
        HistoricTaskInstanceQuery taskQuery = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).finished();
        ArrayList<String> users = new ArrayList<>();
        
        taskQuery.list().forEach(task -> users.add(task.getAssignee()));
        return users;
    }

    public String startMeetingProcess(HashMap<String, Object> formData) {
//        String processInstanceId = runtimeService.startProcessInstanceByKey(ApprovalMeetingForm.MEETING_PROCESS_KEY, formData).getProcessInstanceId();

//        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId)
//                .taskAssignee((String) formData.get(DeleteProcessByIdForm.ASSIGNEE))
//                .singleResult();
//
//        String department = (String) formData.get(ApprovalMeetingForm.DEPARTMENT);
//        String roomId = (String) formData.get(DeleteProcessByIdForm.ROOM_ID);
//        String meetingTime = (String) formData.get(ApprovalMeetingForm.MEETING_TIME);
//
//        HashMap<String, Object> taskData = new HashMap<>();
//        taskData.put(DeleteProcessByIdForm.DEPARTMENT, department);
//        taskData.put(ApprovalMeetingForm.ROOM_ID, roomId);
//        taskData.put(ApprovalMeetingForm.MEETING_TIME, meetingTime);
//
//        taskService.complete(task.getId(), taskData);
//
//        // Schedule the job for the meeting workflow
//        JobDetail jobDetail = JobBuilder.newJob(MeetingWorkflowJob.class).build();
//        JobDataMap jobDataMap = jobDetail.getJobDataMap();
//        jobDataMap.put(DeleteProcessByIdForm.DEPARTMENT, department);
//        jobDataMap.put(ApprovalMeetingForm.PROCESS_INSTANCE_ID, processInstanceId);
//
//        DateTime meetingDateTime = DateUtil.parse(meetingTime, "yyyy-MM-dd HH:mm:ss");
//        quartzUtil.addJob(jobDetail, department, DeleteProcessByIdForm.JOB_NAME, meetingDateTime);

//        return processInstanceId;
        return null;
    }

    // Delete a process instance by its ID
    public void deleteProcessById(String processInstanceId, String userId, String reason) {
//        // Delete process instance if active
//        if (runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).count() > 0) {
//            runtimeService.deleteProcessInstance(processInstanceId, reason);
//        }
//
//        // Delete historical process instance
//        if (historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).count() > 0) {
//            historyService.deleteHistoricProcessInstance(processInstanceId);
//        }
//
//        // Delete associated jobs
//        quartzUtil.deleteJob(processInstanceId, DeleteProcessByIdForm.JOB_NAME);
//        quartzUtil.deleteJob(processInstanceId, ApprovalMeetingForm.CLEANUP_JOB_NAME);
    }

    // Get list of tasks for a user, paginated
    public ArrayList<HashMap<String, Object>> searchUserTaskListByPage(HashMap<String, Object> paginationData) {
//        int pageNumber = (Integer) paginationData.get(ApprovalMeetingForm.PAGE_NUMBER);
//        int pageSize = (Integer) paginationData.get(DeleteProcessByIdForm.PAGE_SIZE);
//        String taskAssignee = (String) paginationData.get(DeleteProcessByIdForm.TASK_ASSIGNEE);
//
//        int offset = (pageNumber - 1) * pageSize;
//        ArrayList<HashMap<String, Object>> taskList = new ArrayList<>();
//
//        if (ApprovalMeetingForm.PENDING_TASK.equals(taskAssignee)) {
//            taskService.createTaskQuery().taskAssignee(taskAssignee)
//                    .orderByTaskCreateTime().desc()
//                    .listPage(offset, pageSize).forEach(task -> {
//                        String processInstanceId = task.getProcessInstanceId();
//                        if (task.getProcessDefinitionId().contains(DeleteProcessByIdForm.MEETING_PROCESS)) {
//                            HashMap<String, Object> taskData = meetingService.searchMeetingByInstanceId(processInstanceId);
//                            taskData.put(ApprovalMeetingForm.TASK_ID, task.getId());
//                            taskList.add(taskData);
//                        }
//                    });
//        } else if (DeleteProcessByIdForm.COMPLETED_TASK.equals(taskAssignee)) {
//            historyService.createHistoricTaskInstanceQuery()
//                    .includeTaskLocalVariables()
//                    .includeProcessVariables()
//                    .taskAssignee(taskAssignee)
//                    .finished()
//                    .orderByTaskCreateTime().desc()
//                    .list().forEach(task -> {
//                        String processInstanceId = task.getProcessInstanceId();
//                        if (task.getProcessDefinitionId().contains(ApprovalMeetingForm.MEETING_PROCESS_KEY)) {
//                            HashMap<String, Object> taskData = meetingService.searchMeetingByInstanceId(processInstanceId);
//                            taskData.put(DeleteProcessByIdForm.COMPLETED_TASK_FLAG, "completed");
//                            taskList.add(taskData);
//                        }
//                    });
//        }

//        return taskList;
        return null;
    }
}

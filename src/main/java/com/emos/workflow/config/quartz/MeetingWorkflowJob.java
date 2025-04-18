package com.emos.workflow.config.quartz;

import java.util.HashMap;

import com.emos.workflow.controller.form.ApprovalMeetingForm;
import com.emos.workflow.controller.form.DeleteProcessByIdForm;
import com.emos.workflow.service.MeetingService;
import com.emos.workflow.service.WorkflowService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class MeetingWorkflowJob extends QuartzJobBean {

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private WorkflowService workflowService;

    private static final Logger logger = LoggerFactory.getLogger(MeetingWorkflowJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

//        // Extract values from jobDataMap
//        String processId = jobDataMap.get(DeleteProcessByIdForm.getProcessIdKey()).toString();
//        String processInstanceId = jobDataMap.get(ApprovalMeetingForm.getMeetingProcessInstanceIdKey()).toString();
//
//        // Check if the process instance exists
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                .processInstanceId(processInstanceId)
//                .singleResult();
//
//        if (processInstance != null) {
//            // Process exists, proceed to delete and update status
//            jobDataMap.put(DeleteProcessByIdForm.getProcessStatusKey(), "PROCESS_TERMINATED");
//            workflowService.deleteProcessById(processId, processInstanceId, "Process Terminated");
//
//            // Update meeting status
//            HashMap<String, Object> updateData = new HashMap<>();
//            updateData.put(ApprovalMeetingForm.getMeetingIdKey(), processId);
//            updateData.put(DeleteProcessByIdForm.getStatusKey(), 5);
//            meetingService.updateMeetingStatus(updateData);
//
//            // Log the completion of the task
//            logger.debug("Meeting workflow process terminated and status updated for meetingId: {}", processId);
//        }
    }
}

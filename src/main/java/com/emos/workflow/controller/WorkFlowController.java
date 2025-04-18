package com.emos.workflow.controller;

import com.emos.workflow.controller.form.*;
import com.emos.workflow.service.WorkflowService;
import com.emos.workflow.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/workflow")
public class WorkFlowController {

    @Autowired
    private WorkflowService workflowService;

    @PostMapping("/approvalMeeting")
    public R approvalMeeting( @RequestBody ApprovalMeetingForm form) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("taskId", form.getTaskId());
        params.put("approval", form.getApproval());
        workflowService.approvalMeeting(params);
        return R.ok();
    }

    @PostMapping("/searchUserTaskListByPage")
    public R searchUserTaskListByPage( @RequestBody SearchUserTaskListByPageForm form) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", form.getUserId());
        params.put("page", form.getPage());
        params.put("length", form.getLength());
        params.put("type", form.getType());
        params.put("token" , form.getToken());

        ArrayList result = workflowService.searchUserTaskListByPage(params);
        return R.ok().put("data", result);
    }

    @PostMapping("/searchProcessUsers")
    public R searchProcessUsers(@Valid @RequestBody SearchProcessUsersForm form) {

        ArrayList users = workflowService.searchProcessUsers(form.getInstanceId());
        return R.ok().put("users", users);
    }

    @PostMapping("/startMeetingProcess")
    public R startMeetingProcess( @RequestBody StartMeetingProcessForm form) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("openId", form.getOpenId());
        params.put("url", form.getUrl());
        params.put("sameDept", form.getSameDept());
        params.put("uuid", form.getUuid());
        params.put("managerId", form.getManagerId());
        params.put("gmId", form.getGmId());
        params.put("date", form.getDate());
        params.put("start", form.getStartTime());
        params.put("creatorId" , form.getCreatorId());
        params.put("identity" , form.getIdentity());
        params.put("department" , form.getDepartment());
        String result = workflowService.startMeetingProcess(params);
        return R.ok().put("instanceId", result);
    }

    @PostMapping("/deleteProcessById")
    public R deleteProcessById(@Valid @RequestBody DeleteProcessByIdForm form) {
        workflowService.deleteProcessById(form.getUuid(), form.getInstanceId(), form.getReason());
        return R.ok();
    }

    @PostMapping("/searchProcessStatus")
    public R searchProcessStatus(@Valid @RequestBody SearchProcessStatusForm form) {


        boolean status = workflowService.searchProcessStatus(form.getInstanceId());
        String statusMessage = status ? "Process Running" : "Process Not Running";
        return R.ok().put("status", statusMessage);
    }
}

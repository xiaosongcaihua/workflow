package com.emos.workflow.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ApprovalMeetingForm {

    @NotBlank
    private String taskId;

    @NotBlank
    private String code;

    @NotBlank
    @Pattern(regexp = "^同意$|^不同意$")
    private String approval;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approvalStatus) {
        this.approval = approvalStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ApprovalMeetingForm that = (ApprovalMeetingForm) obj;
        return taskId.equals(that.taskId) && 
               code.equals(that.code) && 
               approval.equals(that.approval);
    }

    @Override
    public int hashCode() {
        return 31 * taskId.hashCode() + 31 * code.hashCode() + approval.hashCode();
    }

    @Override
    public String toString() {
        return "ApprovalMeetingForm{" +
                "taskId='" + taskId + '\'' +
                ", code='" + code + '\'' +
                ", approvalStatus='" + approval + '\'' +
                '}';
    }
}

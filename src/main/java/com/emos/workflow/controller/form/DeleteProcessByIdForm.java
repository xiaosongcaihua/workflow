package com.emos.workflow.controller.form;

import javax.validation.constraints.NotBlank;

public class DeleteProcessByIdForm {

    @NotBlank
    private String instanceId;

    @NotBlank
    private String reason;

    @NotBlank
    private String code;

    @NotBlank
    private String uuid;

    // Getter and Setter methods

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DeleteProcessByIdForm that = (DeleteProcessByIdForm) obj;

        if (!instanceId.equals(that.instanceId)) return false;
        if (!reason.equals(that.reason)) return false;
        if (!code.equals(that.code)) return false;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        int result = instanceId.hashCode();
        result = 31 * result + reason.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DeleteProcessByIdForm{" +
                "instanceId='" + instanceId + '\'' +
                ", reason='" + reason + '\'' +
                ", code='" + code + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}

package com.emos.workflow.controller.form;

import javax.validation.constraints.NotBlank;

public class SearchProcessStatusForm {

    @NotBlank
    private String instanceId;

    @NotBlank
    private String code;

    // Getter and Setter methods

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SearchProcessStatusForm that = (SearchProcessStatusForm) obj;

        if (!instanceId.equals(that.instanceId)) return false;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        int result = instanceId.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SearchProcessStatusForm{" +
                "instanceId='" + instanceId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    // No need for 'canEqual' method as it's typically used in more complex scenarios
}

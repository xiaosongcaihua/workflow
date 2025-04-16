package com.emos.workflow.controller.form;

import javax.validation.constraints.NotBlank;

public class SearchProcessUsersForm {

    @NotBlank
    private String instanceId;

    @NotBlank
    private String code;

    // Default constructor
    public SearchProcessUsersForm() {
    }

    // Getter and Setter for instanceId
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    // Getter and Setter for code
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // hashCode method to generate a hash based on instanceId and code
    @Override
    public int hashCode() {
        int result = instanceId.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }

    // equals method to compare instanceId and code of two objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SearchProcessUsersForm that = (SearchProcessUsersForm) obj;
        return instanceId.equals(that.instanceId) && code.equals(that.code);
    }

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "SearchProcessUsersForm{" +
                "instanceId='" + instanceId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

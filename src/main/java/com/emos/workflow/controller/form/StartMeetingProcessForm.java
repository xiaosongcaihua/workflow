package com.emos.workflow.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class StartMeetingProcessForm {
    
    private Boolean sameDept;
    
    @NotNull
    @Pattern(regexp = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$")
    private String date;

    @NotBlank
    private String openId;

    @Min(1)
    private Integer managerId;

    @NotBlank
    private String url;

    @Min(1)
    private Integer gmId;

    @NotNull
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$")
    private String startTime;

    @NotBlank
    private String uuid;

    @NotBlank
    private String code;

    // Getters and Setters
    public Boolean getSameDept() {
        return sameDept;
    }

    public void setSameDept(Boolean sameDept) {
        this.sameDept = sameDept;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // Equals and hashCode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StartMeetingProcessForm that = (StartMeetingProcessForm) obj;
        return gmId.equals(that.gmId) &&
                managerId.equals(that.managerId) &&
                sameDept.equals(that.sameDept) &&
                uuid.equals(that.uuid) &&
                openId.equals(that.openId) &&
                url.equals(that.url) &&
                code.equals(that.code) &&
                date.equals(that.date) &&
                startTime.equals(that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gmId, managerId, sameDept, uuid, openId, url, code, date, startTime);
    }

    // toString method
    @Override
    public String toString() {
        return "StartMeetingProcessForm{" +
                "sameDept=" + sameDept +
                ", date='" + date + '\'' +
                ", openId='" + openId + '\'' +
                ", managerId=" + managerId +
                ", url='" + url + '\'' +
                ", gmId=" + gmId +
                ", startTime='" + startTime + '\'' +
                ", uuid='" + uuid + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

package com.emos.workflow.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartMeetingProcessForm {
    
    private Boolean sameDept;
    private String department;

    private String creatorId;
    private String identity;
    
    @NotNull
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

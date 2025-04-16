package com.emos.workflow.db.pojo;

import com.emos.workflow.controller.form.ApprovalMeetingForm;
import com.emos.workflow.controller.form.DeleteProcessByIdForm;
import java.io.Serializable;
import java.util.Date;

public class TbMeeting implements Serializable {

    private String title;
    private Short type;
    private Date createTime;
    private Long id;
    private Short status;
    private String uuid;
    private static final long serialVersionUID = 1L;
    private Long creatorId;
    private String place;
    private String description;
    private String startTime;
    private String endTime;
    private String members;
    private String instanceId;

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setDate(String date) {
        this.uuid = date;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TbMeeting;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getMembers() {
        return members;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return uuid;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 59 * result + (id == null ? 43 : id.hashCode());
        result = 59 * result + (creatorId == null ? 43 : creatorId.hashCode());
        result = 59 * result + (type == null ? 43 : type.hashCode());
        result = 59 * result + (status == null ? 43 : status.hashCode());
        result = 59 * result + (uuid == null ? 43 : uuid.hashCode());
        result = 59 * result + (title == null ? 43 : title.hashCode());
        result = 59 * result + (uuid == null ? 43 : uuid.hashCode());
        result = 59 * result + (place == null ? 43 : place.hashCode());
        result = 59 * result + (startTime == null ? 43 : startTime.hashCode());
        result = 59 * result + (endTime == null ? 43 : endTime.hashCode());
        result = 59 * result + (members == null ? 43 : members.hashCode());
        result = 59 * result + (description == null ? 43 : description.hashCode());
        result = 59 * result + (instanceId == null ? 43 : instanceId.hashCode());
        result = 59 * result + (createTime == null ? 43 : createTime.hashCode());
        return result;
    }

    public String getPlace() {
        return place;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("Id: ").append(id)
            .append(", UUID: ").append(uuid)
            .append(", Title: ").append(title)
            .append(", CreatorId: ").append(creatorId)
            .append(", Date: ").append(uuid)
            .append(", Place: ").append(place)
            .append(", StartTime: ").append(startTime)
            .append(", EndTime: ").append(endTime)
            .append(", Status: ").append(status)
            .append(", Members: ").append(members)
            .append(", Description: ").append(description)
            .append(", InstanceId: ").append(instanceId)
            .append(", CreateTime: ").append(createTime)
            .toString();
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Short getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TbMeeting)) return false;
        TbMeeting other = (TbMeeting) obj;
        return (id != null ? id.equals(other.id) : other.id == null) &&
               (creatorId != null ? creatorId.equals(other.creatorId) : other.creatorId == null) &&
               (type != null ? type.equals(other.type) : other.type == null) &&
               (status != null ? status.equals(other.status) : other.status == null) &&
               (uuid != null ? uuid.equals(other.uuid) : other.uuid == null) &&
               (title != null ? title.equals(other.title) : other.title == null) &&
               (place != null ? place.equals(other.place) : other.place == null) &&
               (startTime != null ? startTime.equals(other.startTime) : other.startTime == null) &&
               (endTime != null ? endTime.equals(other.endTime) : other.endTime == null) &&
               (members != null ? members.equals(other.members) : other.members == null) &&
               (description != null ? description.equals(other.description) : other.description == null) &&
               (instanceId != null ? instanceId.equals(other.instanceId) : other.instanceId == null) &&
               (createTime != null ? createTime.equals(other.createTime) : other.createTime == null);
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Short getStatus() {
        return status;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public TbMeeting() {
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

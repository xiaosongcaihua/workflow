package com.emos.workflow.controller.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class SearchUserTaskListByPageForm {

    @NotNull
    @Min(1L)
    private Integer page;

    @NotBlank
    private String type;

    @NotBlank
    private String userId;

    @NotNull
    @Range(min = 1L, max = 60L)
    private Integer length;

    // Default constructor
    public SearchUserTaskListByPageForm() {
    }

    // Getter and Setter for page
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for code
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and Setter for length
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    // hashCode method to generate a hash based on page, length, type, and code
    @Override
    public int hashCode() {
        int result = page != null ? page.hashCode() : 0;
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    // equals method to compare the objects based on page, length, type, and code
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SearchUserTaskListByPageForm that = (SearchUserTaskListByPageForm) obj;
        return (page != null ? page.equals(that.page) : that.page == null) &&
               (length != null ? length.equals(that.length) : that.length == null) &&
               (type != null ? type.equals(that.type) : that.type == null) &&
               (userId != null ? userId.equals(that.userId) : that.userId == null);
    }

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "SearchUserTaskListByPageForm{" +
                "page=" + page +
                ", type='" + type + '\'' +
                ", code='" + userId + '\'' +
                ", length=" + length +
                '}';
    }
}

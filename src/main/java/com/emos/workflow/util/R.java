package com.emos.workflow.util;

import com.emos.workflow.controller.form.ApprovalMeetingForm;
import com.emos.workflow.controller.form.DeleteProcessByIdForm;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

    // Error response with custom code and message
    public static R error(int code, String message) {
        R response = new R();
        response.put("code", code);
        response.put("message", message);
        return response;
    }

    // Successful response with a map of data
    public static R ok(Map<String, Object> data) {
        R response = new R();
        response.putAll(data);
        return response;
    }

    // Default successful response
    public static R ok() {
        return new R();
    }

    // Successful response with a custom message
    public static R ok(String message) {
        R response = new R();
//        response.put(DeleteProcessByIdForm.ALLATORIxDEMO(",)&"), message);
        return response;
    }

    // Default error response
    public static R error() {
        return error(500, "服务器内部错误");
    }

    // Error response with a custom message
    public static R error(String message) {
        return error(500, message);
    }

    // Default constructor initializing the response with a success code and message

    // Custom put method to return the response itself
    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

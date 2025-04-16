//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.emos.workflow.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface WorkflowService {
    void approvalMeeting(HashMap<String , Object> var);

    boolean searchProcessStatus(String var1);

    ArrayList searchUserTaskListByPage(HashMap<String , Object> var);

    void deleteProcessById(String var1, String var2, String var3);

    ArrayList searchProcessUsers(String var1);

    String startMeetingProcess(HashMap<String , Object> var);
}

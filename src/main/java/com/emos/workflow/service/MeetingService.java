//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.emos.workflow.service;

import java.util.HashMap;
import java.util.List;

public interface MeetingService {
    List<String> searchUserMeetingInMonth(HashMap<String, Object> params);

    HashMap searchMeetingByInstanceId(String var1);

    Long searchRoomIdByUUID(String var1);

    void updateMeetingStatus(HashMap<String, Object> var);

    HashMap searchMeetingByUUID(String var1);
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.emos.workflow.db.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

import java.util.HashMap;
import java.util.List;


@Mapper
public interface TbMeetingDao {
    HashMap searchMeetingByUUID(String var1);

    boolean searchMeetingMembersInSameDept(String var1);

    HashMap searchMeetingByInstanceId(String var1);

    List<String> searchUserMeetingInMonth(HashMap var1);

    int updateMeetingStatus(HashMap var1);
}

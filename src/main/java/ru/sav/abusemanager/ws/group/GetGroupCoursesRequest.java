package ru.sav.abusemanager.ws.group;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getGroupCoursesRequest")
public class GetGroupCoursesRequest {
    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}

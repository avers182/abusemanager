package ru.sav.abusemanager.ws.user;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "getUserGroupsResponse")
public class GetUserGroupsResponse {
    private List<UserGroup> userGroups;

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }
}

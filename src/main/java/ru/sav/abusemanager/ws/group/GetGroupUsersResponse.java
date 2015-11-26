package ru.sav.abusemanager.ws.group;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "getGroupUsersResponse")
public class GetGroupUsersResponse {
    private List<GroupUser> groupUsers;

    public List<GroupUser> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<GroupUser> groupUsers) {
        this.groupUsers = groupUsers;
    }
}

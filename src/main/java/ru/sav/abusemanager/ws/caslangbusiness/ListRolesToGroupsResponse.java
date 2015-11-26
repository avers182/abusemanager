package ru.sav.abusemanager.ws.caslangbusiness;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "listRolesToGroupsResponse")
public class ListRolesToGroupsResponse {
    private List<RoleToGroup> roleToGroups;

    public List<RoleToGroup> getRoleToGroups() {
        return roleToGroups;
    }

    public void setRoleToGroups(List<RoleToGroup> roleToGroups) {
        this.roleToGroups = roleToGroups;
    }
}

package ru.sav.abusemanager.ws.user;

import ru.sav.abusemanager.ws.group.Group;

import java.util.List;

public class UserGroup {
    private Group group;
    private List<String> roles;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

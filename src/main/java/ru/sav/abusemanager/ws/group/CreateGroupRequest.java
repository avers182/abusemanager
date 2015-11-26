package ru.sav.abusemanager.ws.group;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "createGroupRequest")
public class CreateGroupRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

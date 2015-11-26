package ru.sav.abusemanager.ws.group;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deleteGroupRequest")
public class DeleteGroupRequest {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

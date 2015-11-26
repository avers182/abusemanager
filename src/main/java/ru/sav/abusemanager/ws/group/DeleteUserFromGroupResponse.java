package ru.sav.abusemanager.ws.group;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deleteUserFromGroupResponse")
public class DeleteUserFromGroupResponse {
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}

package ru.sav.abusemanager.ws.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deleteUserResponse")
public class DeleteUserResponse {
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}

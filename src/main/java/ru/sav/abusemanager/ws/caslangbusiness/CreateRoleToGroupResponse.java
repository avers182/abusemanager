package ru.sav.abusemanager.ws.caslangbusiness;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "createRoleToGroupResponse")
public class CreateRoleToGroupResponse {
    private Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}

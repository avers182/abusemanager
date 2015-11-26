package ru.sav.abusemanager.ws.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getUserByLoginRequest")
public class GetUserByLoginRequest {
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

package ru.sav.abusemanager.ws.user;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "createUserRequest")
public class CreateUserRequest {
    private String login;
    private String firstName;
    private String secondName;
    private String jabberId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getJabberId() {
        return jabberId;
    }

    public void setJabberId(String jabberId) {
        this.jabberId = jabberId;
    }
}

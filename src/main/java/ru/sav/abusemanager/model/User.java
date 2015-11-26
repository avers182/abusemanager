package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "o_user")
public class User {
    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "user_id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "informsessiontimeout")
    private Boolean informSessionTimeOut = false;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "language")
    private String language = "ru";

    @Column(name = "fontsize")
    private String fontSize = "100";

    @Column(name = "notification_interval")
    private String notificationInterval = "daily";

    @Column(name = "presencemessagespublic")
    private Boolean presenceMessagesPublic = false;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserProperty> userProperties;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Identity> identities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserProperty> getUserProperties() {
        return userProperties;
    }

    public void setUserProperties(List<UserProperty> userProperties) {
        this.userProperties = userProperties;
    }

    public List<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<Identity> identities) {
        this.identities = identities;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getInformSessionTimeOut() {
        return informSessionTimeOut;
    }

    public void setInformSessionTimeOut(Boolean informSessionTimeOut) {
        this.informSessionTimeOut = informSessionTimeOut;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getNotificationInterval() {
        return notificationInterval;
    }

    public void setNotificationInterval(String notificationInterval) {
        this.notificationInterval = notificationInterval;
    }

    public Boolean getPresenceMessagesPublic() {
        return presenceMessagesPublic;
    }

    public void setPresenceMessagesPublic(Boolean presenceMessagesPublic) {
        this.presenceMessagesPublic = presenceMessagesPublic;
    }
}

package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "o_bs_identity")
public class Identity {
    public static final Integer STATUS_PERMANENT = 1;
    public static final Integer STATUS_ACTIV     = 2;
    public static final Integer STATUS_VISIBLE_LIMIT = 100;
    public static final Integer STATUS_LOGIN_DENIED = 101;
    public static final Integer STATUS_DELETED   = 199;
    public static final String DELETED_USER_DELIMITER = "_bkp_";


    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "lastlogin")
    private Date lastLogin;

    @JoinColumn(name = "fk_user_id")
    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "identity")
    private List<Membership> memberships;

    @OneToMany(mappedBy = "identity")
    private List<Authentication> authentications;

    @OneToMany(mappedBy = "identity")
    private List<GroupMember> groupMembers;

    @OneToMany(mappedBy = "identity")
    private List<NotificationSubscription> notificationSubscriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public List<Authentication> getAuthentications() {
        return authentications;
    }

    public void setAuthentications(List<Authentication> authentications) {
        this.authentications = authentications;
    }

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public List<NotificationSubscription> getNotificationSubscriptions() {
        return notificationSubscriptions;
    }

    public void setNotificationSubscriptions(List<NotificationSubscription> notificationSubscriptions) {
        this.notificationSubscriptions = notificationSubscriptions;
    }
}

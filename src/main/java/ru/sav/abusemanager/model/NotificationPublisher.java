package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "o_noti_pub")
public class NotificationPublisher {
    public static final String PUBLISHER_TYPE_COURSE_REMAINING_TIME = "CourseRemainingTime";
    public static final String RESNAME_COURSE_MODULE = "CourseModule";

    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "publisher_id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "publishertype")
    private String publisherType;

    @Column(name = "data")
    private String data;

    @Column(name = "resname")
    private String resname;

    @Column(name = "resid")
    private Long resid;

    @Column(name = "subident")
    private String subident;

    @Column(name = "businesspath")
    private String businessPath;

    @Column(name = "state")
    private Integer state;

    @Column(name = "latestnews")
    private Date latestNews;

    @OneToMany(mappedBy = "notificationPublisher")
    private List<NotificationSubscription> notificationSubscriptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPublisherType() {
        return publisherType;
    }

    public void setPublisherType(String publisherType) {
        this.publisherType = publisherType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public Long getResid() {
        return resid;
    }

    public void setResid(Long resid) {
        this.resid = resid;
    }

    public String getSubident() {
        return subident;
    }

    public void setSubident(String subident) {
        this.subident = subident;
    }

    public String getBusinessPath() {
        return businessPath;
    }

    public void setBusinessPath(String businessPath) {
        this.businessPath = businessPath;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getLatestNews() {
        return latestNews;
    }

    public void setLatestNews(Date latestNews) {
        this.latestNews = latestNews;
    }

    public List<NotificationSubscription> getNotificationSubscriptions() {
        return notificationSubscriptions;
    }

    public void setNotificationSubscriptions(List<NotificationSubscription> notificationSubscriptions) {
        this.notificationSubscriptions = notificationSubscriptions;
    }
}

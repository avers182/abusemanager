package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "o_noti_sub")
public class NotificationSubscription {
    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "publisher_id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "lastmodified")
    private Date lastModified;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "latestemailed")
    private Date latestemailed;

    @JoinColumn(name = "fk_identity")
    @ManyToOne()
    private Identity identity;

    @JoinColumn(name = "fk_publisher")
    @ManyToOne()
    private NotificationPublisher notificationPublisher;


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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLatestemailed() {
        return latestemailed;
    }

    public void setLatestemailed(Date latestemailed) {
        this.latestemailed = latestemailed;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public NotificationPublisher getNotificationPublisher() {
        return notificationPublisher;
    }

    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }
}

package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "o_gp_business")
public class GpBusiness {
    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "group_id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "lastmodified")
    private Date lastModified;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "groupname")
    private String groupName;

    @Column(name = "waitinglist_enabled")
    private Boolean waitingList = false;

    @Column(name = "autocloseranks_enabled")
    private Boolean autoCloseRanks = false;

    @JoinColumn(name = "fk_group_id")
    @ManyToOne()
    private BsGroup bsGroup;

    @JoinColumn(name = "fk_resource")
    @ManyToOne()
    private OlatResource resource;

    @OneToMany(mappedBy = "gpBusiness")
    private List<CASLangBusiness> casLangBusinesses;

//    @JoinColumn(name = "fk_ownergroup")
//    @ManyToOne()
//    private SecGroup ownerGroup;
//
//    @JoinColumn(name = "fk_partipiciantgroup")
//    @ManyToOne()
//    private SecGroup participantGroup;
//
//    @JoinColumn(name = "fk_waitinggroup")
//    @ManyToOne()
//    private SecGroup waitingGroup;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BsGroup getBsGroup() {
        return bsGroup;
    }

    public void setBsGroup(BsGroup bsGroup) {
        this.bsGroup = bsGroup;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public OlatResource getResource() {
        return resource;
    }

    public void setResource(OlatResource resource) {
        this.resource = resource;
    }

    public Boolean getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(Boolean waitingList) {
        this.waitingList = waitingList;
    }

    public Boolean getAutoCloseRanks() {
        return autoCloseRanks;
    }

    public void setAutoCloseRanks(Boolean autoCloseRanks) {
        this.autoCloseRanks = autoCloseRanks;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public List<CASLangBusiness> getCasLangBusinesses() {
        return casLangBusinesses;
    }

    public void setCasLangBusinesses(List<CASLangBusiness> casLangBusinesses) {
        this.casLangBusinesses = casLangBusinesses;
    }

//    public SecGroup getOwnerGroup() {
//        return ownerGroup;
//    }
//
//    public void setOwnerGroup(SecGroup ownerGroup) {
//        this.ownerGroup = ownerGroup;
//    }
//
//    public SecGroup getParticipantGroup() {
//        return participantGroup;
//    }
//
//    public void setParticipantGroup(SecGroup participantGroup) {
//        this.participantGroup = participantGroup;
//    }
//
//    public SecGroup getWaitingGroup() {
//        return waitingGroup;
//    }
//
//    public void setWaitingGroup(SecGroup waitingGroup) {
//        this.waitingGroup = waitingGroup;
//    }
}

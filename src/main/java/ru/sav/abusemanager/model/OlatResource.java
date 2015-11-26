package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="o_olatresource")
public class OlatResource {
    public static final String NAME_BUSINESS_GROUP = "BusinessGroup";

    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name="resource_id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name="resid")
    private Long resId;

    @Column(name="resname")
    private String resName;

    @OneToMany(mappedBy = "resource")
    private List<GpBusiness> gpBusinesses;

//    @OneToMany(mappedBy = "olatResource")
//    private List<RepositoryEntry> repositoryEntries;
//
//    @OneToMany(mappedBy = "olatResource")
//    private List<GpBusinessToResource> gpBusinessToResources;

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resid) {
        this.resId = resid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
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

    public List<GpBusiness> getGpBusinesses() {
        return gpBusinesses;
    }

    public void setGpBusinesses(List<GpBusiness> gpBusinesses) {
        this.gpBusinesses = gpBusinesses;
    }

//    public List<RepositoryEntry> getRepositoryEntries() {
//        return repositoryEntries;
//    }
//
//    public void setRepositoryEntries(List<RepositoryEntry> repositoryEntries) {
//        this.repositoryEntries = repositoryEntries;
//    }
//
//    public List<GpBusinessToResource> getGpBusinessToResources() {
//        return gpBusinessToResources;
//    }
//
//    public void setGpBusinessToResources(List<GpBusinessToResource> gpBusinessToResources) {
//        this.gpBusinessToResources = gpBusinessToResources;
//    }
}
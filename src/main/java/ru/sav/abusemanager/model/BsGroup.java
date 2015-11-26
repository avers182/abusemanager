package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "o_bs_group")
public class BsGroup {
    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "id")
    private Long id;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "g_name")
    private String name;

    @OneToMany(mappedBy = "bsGroup")
    private List<GroupMember> groupMembers;

    @OneToMany(mappedBy = "bsGroup")
    private List<GpBusiness> gpBusinesses;

    @OneToMany(mappedBy = "bsGroup")
    private List<RepositoryEntryToBsGroup> repositoryEntryToBsGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GroupMember> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<GroupMember> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public List<GpBusiness> getGpBusinesses() {
        return gpBusinesses;
    }

    public void setGpBusinesses(List<GpBusiness> gpBusinesses) {
        this.gpBusinesses = gpBusinesses;
    }

    public List<RepositoryEntryToBsGroup> getRepositoryEntryToBsGroups() {
        return repositoryEntryToBsGroups;
    }

    public void setRepositoryEntryToBsGroups(List<RepositoryEntryToBsGroup> repositoryEntryToBsGroups) {
        this.repositoryEntryToBsGroups = repositoryEntryToBsGroups;
    }
}

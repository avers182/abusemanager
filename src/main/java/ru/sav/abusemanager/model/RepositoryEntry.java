package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "o_repositoryentry")
public class RepositoryEntry {
    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "repositoryentry_id")
    private Long id;

    @Column(name = "displayname")
    private String displayName;

    @JoinColumn(name = "fk_olatresource")
    @ManyToOne
    private OlatResource olatResource;

    @OneToMany(mappedBy = "repositoryEntry")
    private List<RepositoryEntryToBsGroup> repositoryEntryToBsGroups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public OlatResource getOlatResource() {
        return olatResource;
    }

    public void setOlatResource(OlatResource olatResource) {
        this.olatResource = olatResource;
    }

    public List<RepositoryEntryToBsGroup> getRepositoryEntryToBsGroups() {
        return repositoryEntryToBsGroups;
    }

    public void setRepositoryEntryToBsGroups(List<RepositoryEntryToBsGroup> repositoryEntryToBsGroups) {
        this.repositoryEntryToBsGroups = repositoryEntryToBsGroups;
    }
}

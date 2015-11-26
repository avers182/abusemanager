package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "o_re_to_group")
public class RepositoryEntryToBsGroup {
    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "id")
    private Long id;

    @Column(name = "creationdate")
    private Date creationDate;

    @JoinColumn(name = "fk_group_id")
    @ManyToOne
    private BsGroup bsGroup;

    @JoinColumn(name = "fk_entry_id")
    @ManyToOne
    private RepositoryEntry repositoryEntry;

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

    public BsGroup getBsGroup() {
        return bsGroup;
    }

    public void setBsGroup(BsGroup bsGroup) {
        this.bsGroup = bsGroup;
    }

    public RepositoryEntry getRepositoryEntry() {
        return repositoryEntry;
    }

    public void setRepositoryEntry(RepositoryEntry repositoryEntry) {
        this.repositoryEntry = repositoryEntry;
    }
}

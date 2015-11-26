package ru.sav.abusemanager.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "o_bs_membership")
public class Membership {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "lastmodified")
    private Timestamp lastModified;

    @Column(name = "creationdate")
    private Timestamp creationDate;

//    @JoinColumn(name = "secgroup_id")
//    @ManyToOne
//    private SecGroup secGroup;

    @JoinColumn(name = "identity_id")
    @ManyToOne
    private Identity identity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public SecGroup getSecGroup() {
//        return secGroup;
//    }
//
//    public void setSecGroup(SecGroup secGroup) {
//        this.secGroup = secGroup;
//    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public Timestamp getLastModified() {
        return lastModified;
    }

    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}

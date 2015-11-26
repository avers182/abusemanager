package ru.sav.abusemanager.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "o_bs_group_member")
public class GroupMember {
    public static final String ROLE_OWNER = "owner";
    public static final String ROLE_COACH = "coach";
    public static final String ROLE_PARTICIPANT = "participant";

    @Id
    @GeneratedValue(generator = "hilo")
    @GenericGenerator(name = "hilo", strategy = "hilo")
    @Column(name = "id")
    private Long id;

    @Column(name = "creationdate")
    private Date creationDate;

    @Column(name = "lastmodified")
    private Date lastModified;

    @Column(name = "g_role")
    private String role;

    @JoinColumn(name = "fk_group_id")
    @ManyToOne
    private BsGroup bsGroup;

    @JoinColumn(name = "fk_identity_id")
    @ManyToOne
    private Identity identity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BsGroup getBsGroup() {
        return bsGroup;
    }

    public void setBsGroup(BsGroup bsGroup) {
        this.bsGroup = bsGroup;
    }
}

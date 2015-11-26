package ru.sav.abusemanager.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "o_bs_authentication")
public class Authentication {
    @Id
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "creationdate")
    private Timestamp creationDate;

    @JoinColumn(name = "identity_fk")
    @ManyToOne
    private Identity identity;

    @Column(name = "provider")
    private String provider;

    @Column(name = "authusername")
    private String authUserName;

    @Column(name = "credential")
    private String credential;

    @Column(name = "salt")
    private String salt;

    @Column(name = "hashalgorithm")
    private String hashAlgorithm;

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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAuthUserName() {
        return authUserName;
    }

    public void setAuthUserName(String authUserName) {
        this.authUserName = authUserName;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }
}

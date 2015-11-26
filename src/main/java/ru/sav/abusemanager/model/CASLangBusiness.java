package ru.sav.abusemanager.model;

import javax.persistence.*;

@Entity
@Table(name = "txse_cas_lang_business")
public class CASLangBusiness {
    @Id
    @GeneratedValue(generator = "sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence", sequenceName = "txse_cas_lang_business_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "cas_authority")
    private String casAuthority;

    @Column(name = "o_user_language")
    private String userLanguage;

    @JoinColumn(name = "o_gp_business_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private GpBusiness gpBusiness;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCasAuthority() {
        return casAuthority;
    }

    public void setCasAuthority(String casAuthority) {
        this.casAuthority = casAuthority;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public GpBusiness getGpBusiness() {
        return gpBusiness;
    }

    public void setGpBusiness(GpBusiness gpBusiness) {
        this.gpBusiness = gpBusiness;
    }
}

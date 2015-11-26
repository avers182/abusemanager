package ru.sav.abusemanager.model;

import javax.persistence.*;

@Entity
@IdClass(UserPropertyCompositeKey.class)
@Table(name = "o_userproperty")
public class UserProperty {
    public final static String FIRST_NAME = "firstName";
    public final static String LAST_NAME = "lastName";
    public final static String EMAIL = "email";

    @Id
    @Column(name = "fk_user_id")
    // spring-data-jpa порет хуйню при сохранении составного ключа, если одна из частей является Entity. Выбирает нормально, при сохранении ебёт мазга. Сделал костылём два свойства на один столбец, заебла.
    private Long userId;

    @JoinColumn(name = "fk_user_id", insertable = false, updatable = false)
    @ManyToOne
    private User user;

    @Id
    @Column(name = "propname")
    private String propName;

    @Column(name = "propvalue")
    private String propValue;

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.userId = user.getId();
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

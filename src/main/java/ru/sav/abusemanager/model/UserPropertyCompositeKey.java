package ru.sav.abusemanager.model;


import java.io.Serializable;

public class UserPropertyCompositeKey implements Serializable {
//    private User user;
//    spring-data-jpa порет хуйню при сохранении составного ключа, если одна из частей является Entity. Выбирает нормально, при сохранении ебёт мазга. Заебала, сдалал костыль с Long.
    private Long userId;
    private String propName;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }
}

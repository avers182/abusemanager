package ru.sav.abusemanager.ws.repositoryentry;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "setCourseTimeLimitRequest")
public class SetCourseTimeLimitRequest {
    private Long courseId;
    private Integer timeLimit;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}

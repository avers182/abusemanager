package ru.sav.abusemanager.ws.repositoryentry;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getCourseTimeLimitResponse")
public class GetCourseTimeLimitResponse {
    private Long courseId;
    private String courseName;
    private Integer timeLimit;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}

package ru.sav.abusemanager.ws.repositoryentry;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getCourseTimeLimitRequest")
public class GetCourseTimeLimitRequest {
    private Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}

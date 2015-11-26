package ru.sav.abusemanager.ws.repositoryentry;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "listCoursesTLResponse")
public class ListCoursesTLResponse {
    private List<CourseInfo> courseInfos;

    public List<CourseInfo> getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(List<CourseInfo> courseInfos) {
        this.courseInfos = courseInfos;
    }
}

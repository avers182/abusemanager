package ru.sav.abusemanager.ws.group;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "getGroupCoursesResponse")
public class GetGroupCoursesResponse {
    private List<String> courses;

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}

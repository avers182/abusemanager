package ru.sav.abusemanager.service;

import ru.sav.abusemanager.model.Property;
import ru.sav.abusemanager.model.RepositoryEntry;
import ru.sav.abusemanager.repositories.PropertyRepository;
import ru.sav.abusemanager.repositories.RepositoryEntryRepository;
import ru.sav.abusemanager.ws.repositoryentry.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RepositoryEntryService {
    static Logger logger = Logger.getLogger(RepositoryEntryService.class);

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    RepositoryEntryRepository repositoryEntryRepository;

    @Autowired
    NotificationService notificationService;

    public ListCoursesResponse listCourses() {
        List<Course> courses = new ArrayList<Course>();
        for (RepositoryEntry repositoryEntry: repositoryEntryRepository.findCourses()) {
            Course course = new Course();
            course.setId(repositoryEntry.getId());
            course.setName(repositoryEntry.getDisplayName());
            courses.add(course);
        }

        ListCoursesResponse response = new ListCoursesResponse();
        response.setCourses(courses);
        return response;
    }

    public ListCoursesTLResponse listCoursesTL() {
        List<CourseInfo> courseInfos = new ArrayList<CourseInfo>();
        for (Object[] i: repositoryEntryRepository.findCoursesWithTimeLimits()) {
            CourseInfo courseInfo = new CourseInfo();
            courseInfo.setCourseId(((BigInteger) i[0]).longValue());
            courseInfo.setCourseName((String) i[1]);
            courseInfo.setTimeLimit(((BigInteger) i[2]).intValue());
            courseInfos.add(courseInfo);
        }

        ListCoursesTLResponse response = new ListCoursesTLResponse();
        response.setCourseInfos(courseInfos);
        return response;
    }

    public GetCourseTimeLimitResponse getCourseTimeLimit(Long courseId) {
        GetCourseTimeLimitResponse response = new GetCourseTimeLimitResponse();
        for (Object[] i: repositoryEntryRepository.getCourseTimeLimit(courseId)) {
            response.setCourseId(((BigInteger) i[0]).longValue());
            response.setCourseName((String) i[1]);
            response.setTimeLimit(((BigInteger) i[2]).intValue());
        }

        return response;
    }

    @Transactional
    public Boolean setCourseTimeLimit(Long courseId, Integer timeLimit) {
        RepositoryEntry entry = repositoryEntryRepository.findOne(courseId);
        if (entry != null) {
            logger.info("Setting time limit " + timeLimit + " days for " + entry.getDisplayName());

            List<Property> properties = propertyRepository.findByResourceTypeNameAndResourceTypeIdAndName(Property.COURSE_MODULE_RESOURCE_TYPE_NAME, entry.getOlatResource().getResId(), Property.TIME_LIMIT_NAME);
            if (properties.size() == 0) {
                Property property = new Property();
                property.setCreationDate(new Date());
                property.setLastModified(new Date());
                property.setResourceTypeName(Property.COURSE_MODULE_RESOURCE_TYPE_NAME);
                property.setResourceTypeId(entry.getOlatResource().getResId());
                property.setName(Property.TIME_LIMIT_NAME);
                property.setLongValue(timeLimit.longValue());
                propertyRepository.save(property);
            } else {
                for (Property property : properties) {
                    if (properties.indexOf(property) == 0) {
                        property.setLongValue(timeLimit.longValue());
                        propertyRepository.save(property);
                    } else {
                        propertyRepository.delete(property);
                    }
                }
            }

            if (timeLimit == 0) {
                notificationService.deleteSubscriptions(entry.getOlatResource());
            } else {
                notificationService.createSubscriptions(entry.getOlatResource());
            }

            return true;
        } else {
            return  false;
        }
    }
}

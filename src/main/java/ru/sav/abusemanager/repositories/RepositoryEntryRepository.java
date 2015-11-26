package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.RepositoryEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface RepositoryEntryRepository extends CrudRepository<RepositoryEntry, Long> {
    @Query("select re from RepositoryEntry re join re.repositoryEntryToBsGroups rrr join rrr.bsGroup sg join sg.gpBusinesses bg where bg.id = :groupId")
    List<RepositoryEntry> findByGpBusinessId(@Param("groupId") Long groupId);

    @Query("select re from RepositoryEntry re join re.olatResource ore where ore.resName = 'CourseModule'")
    List<RepositoryEntry> findCourses();

    @Query(value =
            "select e.repositoryentry_id, e.displayname, coalesce(p.longvalue, 0) as time_limit" +
                    " from o_repositoryentry e" +
                    "  inner join o_olatresource r on r.resource_id = e.fk_olatresource" +
                    "  left join o_property p on p.resourcetypeid = r.resid and p.name = 'time_limit'" +
                    " where r.resname = 'CourseModule'" +
                    " order by e.repositoryentry_id",
            nativeQuery = true
    )
    List<Object[]>  findCoursesWithTimeLimits();

    @Query(value =
            "select e.repositoryentry_id, e.displayname, coalesce(p.longvalue, 0) as time_limit" +
                    " from o_repositoryentry e" +
                    "  inner join o_olatresource r on r.resource_id = e.fk_olatresource" +
                    "  left join o_property p on p.resourcetypeid = r.resid and p.name = 'time_limit'" +
                    " where r.resname = 'CourseModule'" +
                    "  and e.repositoryentry_id = :courseId" +
                    " order by e.repositoryentry_id" +
                    " limit 1",
            nativeQuery = true
    )
    List<Object[]> getCourseTimeLimit(@Param("courseId") Long courseId);
}

package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.GpBusiness;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface GpBusinessRepository extends CrudRepository<GpBusiness, Long> {
    @Query("select distinct gpb from GpBusiness gpb join fetch gpb.bsGroup bsg join fetch bsg.groupMembers gm join gm.identity i where lower(i.name) = lower(:name)")
    public List<GpBusiness> findByIdentityName(@Param("name") String name);

    @Query("select distinct gpb from GpBusiness gpb join fetch gpb.bsGroup bsg join fetch bsg.groupMembers gm join gm.identity i where lower(i.name) = lower(:name) and gm.role in :roles")
    public List<GpBusiness> findByIdentityNameAndRoles(@Param("name") String name, @Param("roles") List<String> roles);
}

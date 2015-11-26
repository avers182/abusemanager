package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.Identity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface IdentityRepository extends CrudRepository<Identity, Long>{
    public Identity findByNameIgnoreCase(String name);

    @Query("select distinct i from Identity i join fetch i.groupMembers gm join gm.bsGroup bsg join bsg.gpBusinesses gpb where gpb.id = :id")
    public List<Identity> findByGpBusinessId(@Param("id") Long gpBusinessId);

    @Query("select distinct i from Identity i join fetch i.groupMembers gm join gm.bsGroup bsg join bsg.gpBusinesses gpb where gpb.id = :id and gm.role in :roles")
    public List<Identity> findByGpBusinessIdAdnRoles(@Param("id") Long gpBusinessId, @Param("roles") List<String> roles);

    @Query(value =
            "select i.*" +
                    "  from o_olatresource res" +
                    "    inner join o_repositoryentry re on re.fk_olatresource = res.resource_id" +
                    "    inner join o_re_to_group rtg on rtg.fk_entry_id = re.repositoryentry_id" +
                    "    inner join o_bs_group_member gm on gm.fk_group_id = rtg.fk_group_id" +
                    "    inner join o_bs_identity i on i.id = gm.fk_identity_id and gm.g_role = 'participant'" +
                    "    inner join o_property p on p.resourcetypeid = res.resid and p.name = 'time_limit'" +
                    "  where gm.creationdate > (now() - interval '1 day' * p.longvalue)" +
                    "    and res.resid = :resid",
            nativeQuery = true)
    public List<Identity> findByResidTimeLimitNotExceeded(@Param("resid") Long resid);

    @Query(value =
            "select i.*" +
                    "  from o_olatresource res" +
                    "    inner join o_repositoryentry re on re.fk_olatresource = res.resource_id" +
                    "    inner join o_re_to_group rtg on rtg.fk_entry_id = re.repositoryentry_id" +
                    "    inner join o_bs_group_member gm on gm.fk_group_id = rtg.fk_group_id" +
                    "    inner join o_bs_identity i on i.id = gm.fk_identity_id and gm.g_role = 'participant'" +
                    "    inner join o_property p on p.resourcetypeid = res.resid and p.name = 'time_limit'" +
                    "  where gm.creationdate > (now() - interval '1 day' * p.longvalue)" +
                    "    and res.resid = :resid" +
                    "    and i.id not in (" +
                    "      select s.fk_identity" +
                    "        from o_noti_pub pu" +
                    "          inner join o_noti_sub s on s.fk_publisher = pu.publisher_id" +
                    "        where pu.resid = res.resid" +
                    "    )",
            nativeQuery = true)
    public List<Identity> findByResidTimeLimitNotExceededNotSubscribed(@Param("resid") Long resid);
}

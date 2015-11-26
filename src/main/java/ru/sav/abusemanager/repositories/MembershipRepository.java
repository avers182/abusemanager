package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.Identity;
import ru.sav.abusemanager.model.Membership;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface MembershipRepository extends CrudRepository<Membership, Long>{
    List<Membership> deleteByIdentity(Identity identity);
}

package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.Authentication;
import ru.sav.abusemanager.model.Identity;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface AuthenticationRepository extends CrudRepository<Authentication, Long>{
    List<Authentication> deleteByIdentity(Identity identity);
}

package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.User;
import ru.sav.abusemanager.model.UserProperty;
import ru.sav.abusemanager.model.UserPropertyCompositeKey;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface UserPropertyRepository extends CrudRepository<UserProperty, UserPropertyCompositeKey>{
    List<UserProperty> deleteByUser(User user);
    UserProperty findByUserAndPropName(User user, String propName);
}

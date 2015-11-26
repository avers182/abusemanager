package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface UserRepository extends CrudRepository<User, Long>{
}

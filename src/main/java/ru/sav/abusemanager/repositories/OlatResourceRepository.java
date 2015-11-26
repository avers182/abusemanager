package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.OlatResource;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface OlatResourceRepository extends CrudRepository<OlatResource, Long> {
}

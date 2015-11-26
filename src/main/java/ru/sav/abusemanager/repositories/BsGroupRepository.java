package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.BsGroup;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface BsGroupRepository extends CrudRepository<BsGroup, Long> {
}

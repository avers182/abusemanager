package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.BsGroup;
import ru.sav.abusemanager.model.RepositoryEntryToBsGroup;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface RepositoryEntryToBsGroupRepository extends CrudRepository<RepositoryEntryToBsGroup, Long> {
    List<RepositoryEntryToBsGroup> deleteByBsGroup(BsGroup bsGroup);
}

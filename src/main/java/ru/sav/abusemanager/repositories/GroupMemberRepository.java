package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.BsGroup;
import ru.sav.abusemanager.model.GroupMember;
import ru.sav.abusemanager.model.Identity;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface GroupMemberRepository extends CrudRepository<GroupMember, Long>{
    List<GroupMember> deleteByIdentity(Identity identity);
    List<GroupMember> deleteByBsGroup(BsGroup bsGroup);
    List<GroupMember> deleteByIdentityAndBsGroup(Identity identity, BsGroup bsGroup);
    List<GroupMember> deleteByIdentityAndBsGroupAndRoleIn(Identity identity, BsGroup bsGroup, List<String> roles);
}

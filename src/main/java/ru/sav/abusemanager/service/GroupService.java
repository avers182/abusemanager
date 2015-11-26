package ru.sav.abusemanager.service;

import ru.sav.abusemanager.model.*;
import ru.sav.abusemanager.repositories.*;
import ru.sav.abusemanager.ws.group.Group;
import ru.sav.abusemanager.ws.group.GroupUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    BsGroupRepository bsGroupRepository;

    @Autowired
    CASLangBusinessRepository casLangBusinessRepository;

    @Autowired
    GpBusinessRepository gpBusinessRepository;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    OlatResourceRepository olatResourceRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    RepositoryEntryRepository repositoryEntryRepository;

    @Autowired
    RepositoryEntryToBsGroupRepository repositoryEntryToBsGroupRepository;

    @Autowired
    UserPropertyRepository userPropertyRepository;

    static Logger logger = Logger.getLogger(GroupService.class);

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<Group>();
        for (GpBusiness gpBusiness: gpBusinessRepository.findAll()) {
            Group group = new Group();
            group.setId(gpBusiness.getId());
            group.setName(gpBusiness.getGroupName());
            groups.add(group);
        }
        return groups;
    }

    @Transactional
    public Boolean createGroup(String name) {
        logger.info("Creating group \"" + name + "\"");

        Date now = new Date();

        BsGroup bsGroup = new BsGroup();
        bsGroup.setCreationDate(now);
        bsGroupRepository.save(bsGroup);

        GpBusiness gpBusiness = new GpBusiness();
        gpBusiness.setLastModified(now);
        gpBusiness.setCreationDate(now);
        gpBusiness.setBsGroup(bsGroup);
        gpBusiness.setGroupName(name);
        gpBusinessRepository.save(gpBusiness);

        OlatResource olatResource = new OlatResource();
        olatResource.setCreationDate(now);
        olatResource.setResName(OlatResource.NAME_BUSINESS_GROUP);
        olatResource.setResId(gpBusiness.getId());
        olatResourceRepository.save(olatResource);

        gpBusiness.setResource(olatResource);
        gpBusinessRepository.save(gpBusiness);

        return true;
    }

    @Transactional
    public Boolean updateGroup(Long id, String name) {
        GpBusiness gpBusiness = gpBusinessRepository.findOne(id);
        if (gpBusiness != null && !"".equals(name) && name != null) {
            logger.info("Updating group id = " + id + ", new name is \"" + name + "\"");

            gpBusiness.setGroupName(name);
            gpBusinessRepository.save(gpBusiness);

            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean deleteGroup(Long id) {
        GpBusiness gpBusiness = gpBusinessRepository.findOne(id);
        if (gpBusiness != null) {
            logger.info("Deleting group " + id);

            casLangBusinessRepository.deleteByGpBusinessId(id);
            propertyRepository.deleteByResourceTypeNameAndResourceTypeId(Property.BUSINESS_GROUP_RESOURCE_TYPE_NAME, id);

            gpBusinessRepository.delete(gpBusiness);

            olatResourceRepository.delete(gpBusiness.getResource());
            groupMemberRepository.deleteByBsGroup(gpBusiness.getBsGroup());
            repositoryEntryToBsGroupRepository.deleteByBsGroup(gpBusiness.getBsGroup());
            bsGroupRepository.delete(gpBusiness.getBsGroup());

            return true;
        } else {
            return false;
        }
    }

    public List<GroupUser> getGroupUsers(Long gpBusinessId, List<String> roles) {
        List<GroupUser> groupUsers = new ArrayList<GroupUser>();
        List<Identity> identities;

        if (roles == null) {
            identities = identityRepository.findByGpBusinessId(gpBusinessId);
        } else if (roles.size() == 0) {
            identities = identityRepository.findByGpBusinessId(gpBusinessId);
        } else {
            identities = identityRepository.findByGpBusinessIdAdnRoles(gpBusinessId, roles);
        }

        for (Identity i: identities) {
            GroupUser groupUser = new GroupUser();

            groupUser.setLogin(i.getName());

            UserProperty userProperty;
            userProperty = userPropertyRepository.findByUserAndPropName(i.getUser(), UserProperty.FIRST_NAME);
            if (userProperty != null) {
                groupUser.setFirstName(userProperty.getPropValue());
            }
            userProperty = userPropertyRepository.findByUserAndPropName(i.getUser(), UserProperty.LAST_NAME);
            if (userProperty != null) {
                groupUser.setSecondName(userProperty.getPropValue());
            }

            List<String> rls = new ArrayList<String>();
            for (GroupMember gm: i.getGroupMembers()) {
                rls.add(gm.getRole());
            }
            groupUser.setRoles(rls);

            groupUsers.add(groupUser);
        }

        return groupUsers;
    }

    public Boolean addUserToGroup(String login, Long groupId, List<String> roles) {
        if (roles == null) {
            roles = new ArrayList<String>();
        }
        if (roles.size() == 0) {
            roles.add(GroupMember.ROLE_PARTICIPANT);
        }

        Identity identity = identityRepository.findByNameIgnoreCase(login);
        GpBusiness gpBusiness = gpBusinessRepository.findOne(groupId);

        if (identity != null && gpBusiness != null) {
            for (String s: roles) {
                logger.info("Adding user \"" + login + "\" to group " + groupId + " with role \"" + s);

                Date now = new Date();
                GroupMember gm = new GroupMember();
                gm.setBsGroup(gpBusiness.getBsGroup());
                gm.setIdentity(identity);
                gm.setCreationDate(now);
                gm.setLastModified(now);
                gm.setRole(s);

                groupMemberRepository.save(gm);
            }

            return true;
        } else {
            return false;
        }

    }

    @Transactional
    public Boolean deleteUserFromGroup(String login, Long groupId, List<String> roles) {
        Identity identity = identityRepository.findByNameIgnoreCase(login);
        GpBusiness gpBusiness = gpBusinessRepository.findOne(groupId);

        if (identity != null && gpBusiness != null) {
            logger.info("Deleting user \"" + login + "\" from group " + groupId);

            if (roles == null) {
                groupMemberRepository.deleteByIdentityAndBsGroup(identity, gpBusiness.getBsGroup());
            } else if (roles.size() == 0) {
                groupMemberRepository.deleteByIdentityAndBsGroup(identity, gpBusiness.getBsGroup());
            } else {
                groupMemberRepository.deleteByIdentityAndBsGroupAndRoleIn(identity, gpBusiness.getBsGroup(), roles);
            }

            return true;
        } else {
            return false;
        }
    }

    public List<String> getGroupCourses(Long groupId) {
        List<String> courses = new ArrayList<String>();

        for (RepositoryEntry re: repositoryEntryRepository.findByGpBusinessId(groupId)) {
            courses.add(re.getDisplayName());
        }

        return courses;
    }
}

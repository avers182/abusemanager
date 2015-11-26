package ru.sav.abusemanager.service;

import ru.sav.abusemanager.model.*;
import ru.sav.abusemanager.repositories.*;
import ru.sav.abusemanager.ws.group.Group;
import ru.sav.abusemanager.ws.user.GetUserByLoginResponse;
import ru.sav.abusemanager.ws.user.UserGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @Autowired
    private GpBusinessRepository gpBusinessRepository;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPropertyRepository userPropertyRepository;

    public GetUserByLoginResponse getUserByLogin(String login) {
        GetUserByLoginResponse response = new GetUserByLoginResponse();
        Identity identity = identityRepository.findByNameIgnoreCase(login);

        if (identity != null) {
            response.setLogin(identity.getName());

            UserProperty userProperty;
            userProperty = userPropertyRepository.findByUserAndPropName(identity.getUser(), UserProperty.FIRST_NAME);
            if (userProperty != null) {
                response.setFirstName(userProperty.getPropValue());
            }
            userProperty = userPropertyRepository.findByUserAndPropName(identity.getUser(), UserProperty.LAST_NAME);
            if (userProperty != null) {
                response.setSecondName(userProperty.getPropValue());
            }
            userProperty = userPropertyRepository.findByUserAndPropName(identity.getUser(), UserProperty.EMAIL);
            if (userProperty != null) {
                response.setJabberId(userProperty.getPropValue());
            }
        }

        return response;
    }

    public Boolean createUser(String login, String firstName, String secondName, String jabberId) {
        logger.info("createUser, login: " + login + "; firstName: " + firstName + "; secondName: " + secondName + "; jabberId: " + jabberId);

        if (identityRepository.findByNameIgnoreCase(login) == null) {
            Date now = new Date();

            User user = new User();
            user.setUserProperties(new ArrayList<UserProperty>());
            user.setCreationDate(now);
            userRepository.save(user);

//            UserProperty p1 = new UserProperty();
//            p1.setUser(user);
//            p1.setPropName("userName");
//            p1.setPropValue(login.toLowerCase());
//            userPropertyRepository.save(p1);

            UserProperty p2 = new UserProperty();
            p2.setUser(user);
            p2.setPropName(UserProperty.FIRST_NAME);
            p2.setPropValue(firstName);
            userPropertyRepository.save(p2);

            UserProperty p3 = new UserProperty();
            p3.setUser(user);
            p3.setPropName(UserProperty.LAST_NAME);
            p3.setPropValue(secondName);
            userPropertyRepository.save(p3);

            UserProperty p4 = new UserProperty();
            p4.setUser(user);
            p4.setPropName(UserProperty.EMAIL);
            p4.setPropValue(jabberId);
            userPropertyRepository.save(p4);

            Identity identity = new Identity();
            identity.setName(login.toLowerCase());
            identity.setUser(user);
            identity.setCreationDate(now);
            identity.setLastLogin(now);
            identity.setStatus(Identity.STATUS_ACTIV);
            identityRepository.save(identity);

            return true;
        } else {
            return false;
        }
    }

    public Boolean updateUser(String login, String firstName, String secondName, String jabberId) {
        logger.info("updateUser, login: " + login + "; firstName: " + firstName + "; secondName: " + secondName + "; jabberId: " + jabberId);

        Identity identity = identityRepository.findByNameIgnoreCase(login);
        if (identity != null) {
            for (UserProperty userProperty: identity.getUser().getUserProperties()) {
                if (userProperty.getPropName().equals("firstName") && firstName != null && !"".equals(firstName) && !userProperty.getPropValue().equals(firstName)) {
                    userProperty.setPropValue(firstName);
                    userPropertyRepository.save(userProperty);
                }
                if (userProperty.getPropName().equals("lastName") && secondName != null && !"".equals(secondName) && !userProperty.getPropValue().equals(secondName)) {
                    userProperty.setPropValue(secondName);
                    userPropertyRepository.save(userProperty);
                }
                if (userProperty.getPropName().equals("email") && jabberId != null && !"".equals(jabberId) && !userProperty.getPropValue().equals(jabberId)) {
                    userProperty.setPropValue(jabberId);
                    userPropertyRepository.save(userProperty);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean deleteUser(String login) {
        logger.info("deleteUser, login: " + login);

        Identity identity = identityRepository.findByNameIgnoreCase(login);
        if (identity != null) {
            userPropertyRepository.deleteByUser(identity.getUser());
            authenticationRepository.deleteByIdentity(identity);
            membershipRepository.deleteByIdentity(identity);
            groupMemberRepository.deleteByIdentity(identity);

            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            identity.setName(dateFormat.format(new Date()) + Identity.DELETED_USER_DELIMITER + identity.getName());

            identity.setStatus(Identity.STATUS_DELETED);

            return true;
        } else {
            return false;
        }
    }

    public List<UserGroup> getUserGroups(String login, List<String> roles){
        List<UserGroup> groups = new ArrayList<UserGroup>();
        List<GpBusiness> gpBusinesses;

        if (roles == null) {
            gpBusinesses = gpBusinessRepository.findByIdentityName(login);
        } else if (roles.size() == 0) {
            gpBusinesses = gpBusinessRepository.findByIdentityName(login);
        } else {
            gpBusinesses = gpBusinessRepository.findByIdentityNameAndRoles(login, roles);
        }

        for (GpBusiness gpBusiness: gpBusinesses) {
            UserGroup userGroup = new UserGroup();

            Group group = new Group();
            group.setName(gpBusiness.getGroupName());
            group.setId(gpBusiness.getId());
            userGroup.setGroup(group);

            List<String> rls = new ArrayList<String>();
            for (GroupMember groupMember: gpBusiness.getBsGroup().getGroupMembers()) {
                rls.add(groupMember.getRole());
            }
            userGroup.setRoles(rls);

            groups.add(userGroup);
        }

        return groups;
    }
}

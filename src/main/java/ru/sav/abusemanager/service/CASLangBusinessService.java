package ru.sav.abusemanager.service;

import ru.sav.abusemanager.model.CASLangBusiness;
import ru.sav.abusemanager.model.GpBusiness;
import ru.sav.abusemanager.repositories.CASLangBusinessRepository;
import ru.sav.abusemanager.repositories.GpBusinessRepository;
import ru.sav.abusemanager.ws.caslangbusiness.RoleToGroup;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CASLangBusinessService {
    static Logger logger = Logger.getLogger(CASLangBusinessService.class);

    @Autowired
    CASLangBusinessRepository casLangBusinessRepository;

    @Autowired
    GpBusinessRepository gpBusinessRepository;

    private RoleToGroup zzz(CASLangBusiness c) {
        RoleToGroup r = new RoleToGroup();

        r.setId(c.getId());
        r.setRole(c.getCasAuthority());
        if (c.getGpBusiness() != null) {
            r.setGroupId(c.getGpBusiness().getId());
            r.setGroupName(c.getGpBusiness().getGroupName());
        }

        return r;
    }

    public List<RoleToGroup> listRolesToGroups() {
        List<RoleToGroup> list = new ArrayList<RoleToGroup>();
        for (CASLangBusiness c: casLangBusinessRepository.findAll()) {
            list.add(zzz(c));
        }

        return list;
    }

    public List<RoleToGroup> getRoleToGroup(Long id, String role, Long groupId) {
        List<RoleToGroup> list = new ArrayList<RoleToGroup>();

        if (id != null) {
            CASLangBusiness c = casLangBusinessRepository.findOne(id);

            if (c != null) {
                list.add(zzz(c));
            }
        } else if (groupId == null) {
            for (CASLangBusiness c: casLangBusinessRepository.findByCasAuthority(role)) {
                list.add(zzz(c));
            }
        } else if ("".equals(role) || role == null) {
            for (CASLangBusiness c: casLangBusinessRepository.findByGpBusinessId(groupId)) {
                list.add(zzz(c));
            }
        } else {
            for (CASLangBusiness c: casLangBusinessRepository.findByCasAuthorityAndGpBusinessId(role, groupId)) {
                list.add(zzz(c));
            }
        }

        return list;
    }

    public Boolean createRoleToGroup(String role, Long groupId) {
        GpBusiness g = gpBusinessRepository.findOne(groupId);

        if (g != null) {
            logger.info("Creating role-to-group link, role: " + role + ", groupId: " + groupId);

            CASLangBusiness c = new CASLangBusiness();

            c.setCasAuthority(role);
            c.setGpBusiness(g);

            casLangBusinessRepository.save(c);
            return true;
        } else {
            return false;
        }
    }

    public Boolean updateRoleToGroup(Long id, String role, Long groupId) {
        CASLangBusiness c = casLangBusinessRepository.findOne(id);
        GpBusiness b = gpBusinessRepository.findOne(groupId);

        if (c != null && b != null) {
            logger.info("Updating role-to-group link, role: " + role + ", groupId: " + groupId);

            c.setCasAuthority(role);
            c.setGpBusiness(b);
            casLangBusinessRepository.save(c);

            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Boolean deleteRoleToGroup(Long id, String role, Long groupId) {
        logger.info("Deleting role-to-group link, role: " + role + ", groupId: " + groupId);

        if (id != null) {
            casLangBusinessRepository.delete(id);
        } else if (groupId == null) {
            casLangBusinessRepository.deleteByCasAuthority(role);
        } else if ("".equals(role) || role == null) {
            casLangBusinessRepository.deleteByGpBusinessId(groupId);
        } else {
            casLangBusinessRepository.deleteByCasAuthorityAndGpBusinessId(role, groupId);
        }
        return true;
    }
}

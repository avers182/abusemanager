package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.CASLangBusiness;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface CASLangBusinessRepository extends CrudRepository<CASLangBusiness, Long> {
    List<CASLangBusiness> findByCasAuthority(String casAuthoity);
    List<CASLangBusiness> findByGpBusinessId(Long id);
    List<CASLangBusiness> findByCasAuthorityAndGpBusinessId(String casAuthoity, Long id);

    List<CASLangBusiness> deleteByCasAuthority(String casAuthoity);
    List<CASLangBusiness> deleteByGpBusinessId(Long id);
    List<CASLangBusiness> deleteByCasAuthorityAndGpBusinessId(String casAuthoity, Long id);
}

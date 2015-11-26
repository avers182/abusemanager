package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.Property;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface PropertyRepository extends CrudRepository<Property, Long> {
    public List<Property> deleteByResourceTypeNameAndResourceTypeId(String resourceTypeName, Long resourceTypeId);
    public List<Property> findByResourceTypeNameAndResourceTypeIdAndName(String resourceTypeName, Long resourceTypeId, String name);
}

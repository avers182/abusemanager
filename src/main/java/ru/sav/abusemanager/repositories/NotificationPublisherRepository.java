package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.NotificationPublisher;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface NotificationPublisherRepository extends CrudRepository<NotificationPublisher, Long>{
    List<NotificationPublisher> findByPublisherTypeAndResid(String publisherType, Long resid);
}

package ru.sav.abusemanager.repositories;

import ru.sav.abusemanager.model.NotificationPublisher;
import ru.sav.abusemanager.model.NotificationSubscription;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface NotificationSubscriptionRepository extends CrudRepository<NotificationSubscription, Long> {
    List<NotificationSubscription> deleteByNotificationPublisher(NotificationPublisher publisher);
}

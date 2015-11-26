package ru.sav.abusemanager.service;

import ru.sav.abusemanager.model.Identity;
import ru.sav.abusemanager.model.NotificationPublisher;
import ru.sav.abusemanager.model.NotificationSubscription;
import ru.sav.abusemanager.model.OlatResource;
import ru.sav.abusemanager.repositories.IdentityRepository;
import ru.sav.abusemanager.repositories.NotificationPublisherRepository;
import ru.sav.abusemanager.repositories.NotificationSubscriptionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    static Logger logger = Logger.getLogger(NotificationService.class);

    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    NotificationPublisherRepository notificationPublisherRepository;

    @Autowired
    NotificationSubscriptionRepository notificationSubscriptionRepository;

    public void createSubscriptions(OlatResource resource) {
        NotificationPublisher publisher;
        Date now = new Date();
        List<NotificationPublisher> publishers = notificationPublisherRepository.findByPublisherTypeAndResid(NotificationPublisher.PUBLISHER_TYPE_COURSE_REMAINING_TIME, resource.getResId());

        logger.info("Creating subscriptions for resid=" + resource.getResId());

        if (publishers.size() > 0) {
            publisher = publishers.get(0);
        } else {
            publisher = new NotificationPublisher();
            publisher.setCreationDate(now);
            publisher.setLatestNews(now);
            publisher.setPublisherType(NotificationPublisher.PUBLISHER_TYPE_COURSE_REMAINING_TIME);
            publisher.setResname(NotificationPublisher.RESNAME_COURSE_MODULE);
            publisher.setResid(resource.getResId());
            publisher.setState(0);
            notificationPublisherRepository.save(publisher);
        }

        for (Identity i: identityRepository.findByResidTimeLimitNotExceededNotSubscribed(resource.getResId())) {
            NotificationSubscription subscription = new NotificationSubscription();
            subscription.setLastModified(now);
            subscription.setCreationDate(now);
            subscription.setLatestemailed(now);
            subscription.setIdentity(i);
            subscription.setNotificationPublisher(publisher);
            notificationSubscriptionRepository.save(subscription);
        }
    }

    public void deleteSubscriptions(OlatResource resource) {
        logger.info("Deleting subscriptions for resid=" + resource.getResId());

        for (NotificationPublisher publisher: notificationPublisherRepository.findByPublisherTypeAndResid(NotificationPublisher.PUBLISHER_TYPE_COURSE_REMAINING_TIME, resource.getResId())) {
            notificationSubscriptionRepository.deleteByNotificationPublisher(publisher);
        }
    }
}

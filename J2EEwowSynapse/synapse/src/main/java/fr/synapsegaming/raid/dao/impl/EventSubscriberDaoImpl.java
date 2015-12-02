package fr.synapsegaming.raid.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.EventSubscriberDao;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.entity.EventSubscriber;
import fr.synapsegaming.user.entity.User;

@Repository("EventSubscriberDao")
public class EventSubscriberDaoImpl extends AbstractDao<EventSubscriber, Long>
        implements EventSubscriberDao {

    private static final Logger LOGGER = Logger
            .getLogger(EventSubscriberDaoImpl.class);

    @Override
    public EventSubscriber find(Event event, User user) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from EventSubscriber es join fetch es.event e join fetch es.user u where e.id = :idEvent and u.id = :idUser");
            query.setParameter("idEvent", event.getId());
            query.setParameter("idUser", user.getId());
            return (EventSubscriber) query.uniqueResult();
        } catch (Exception e) {
            LOGGER.warn(e);
            return null;
        } finally {
            session.close();
        }
    }

}

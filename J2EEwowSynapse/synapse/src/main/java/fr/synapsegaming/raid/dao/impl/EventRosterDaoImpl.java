package fr.synapsegaming.raid.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.EventRosterDao;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.entity.EventRoster;
import fr.synapsegaming.user.entity.User;

@Repository("EventRosterDao")
public class EventRosterDaoImpl extends AbstractDao<EventRoster, Long>
        implements EventRosterDao {

    private static final Logger LOGGER = Logger
            .getLogger(EventRosterDaoImpl.class);

    @Override
    public EventRoster find(Event event, User user) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from EventRoster er join fetch er.event e join fetch er.user u where e.id = :idEvent and u.id = :idUser");
            query.setParameter("idEvent", event.getId());
            query.setParameter("idUser", user.getId());
            return (EventRoster) query.uniqueResult();
        } catch (Exception e) {
            LOGGER.warn(e);
            return null;
        } finally {
            session.close();
        }
    }

}

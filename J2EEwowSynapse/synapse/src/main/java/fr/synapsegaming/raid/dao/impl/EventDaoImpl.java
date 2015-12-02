package fr.synapsegaming.raid.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.EventDao;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.user.entity.User;

@Repository("EventDao")
public class EventDaoImpl extends AbstractDao<Event, Long> implements EventDao {

    private static final Logger LOGGER = Logger.getLogger(EventDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Event> getEvents(Date date) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Event e where MONTH(e.date) = MONTH(:date)");
            query.setParameter("date", date);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList<Event>();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Event> getEvents(int month, int year) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Event e where MONTH(e.date) = :month and YEAR(e.date) = :year");
            query.setParameter("month", month);
            query.setParameter("year", year);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList<Event>();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsersForRoleAndEvent(long idEvent, long code) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from User u join fetch u.eventSubscribers es join fetch es.event e join fetch u.spec s join fetch s.role r where r.id = :idRole and e.id = :idEvent");
            query.setParameter("idEvent", idEvent);
            query.setParameter("idRole", code);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getRosterUsersForRoleAndEvent(long idEvent, long code) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from User u join fetch u.roster rs join fetch rs.event e join fetch u.spec s join fetch s.role r where r.id = :idRole and e.id = :idEvent");
            query.setParameter("idEvent", idEvent);
            query.setParameter("idRole", code);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

}

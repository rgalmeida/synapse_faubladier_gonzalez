package fr.synapsegaming.raid.dao;

import java.util.Date;
import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.user.entity.User;

public interface EventDao extends Dao<Event, Long> {

    /**
     * List all the events for the parameter month
     * 
     * @param date
     *            : the current date
     * @return a list of events
     */
    public List<Event> getEvents(Date date);

    /**
     * List all the events for specified month and year
     * 
     * @param month
     *            : event's month
     * @param year
     *            : events' year
     * @return
     */
    public List<Event> getEvents(int month, int year);

    /**
     * Get a list of users who subscribed to an event for a role
     * 
     * @param idEvent
     *            : the event where the users subscribed
     * @param code
     *            : the unique ID of the role
     * @return a list of users
     */
    public List<User> getUsersForRoleAndEvent(long idEvent, long code);

    /**
     * Get the list of users of a role in the roster for the event
     * 
     * @param idEvent
     *            : the event
     * @param code
     *            : the role lokked for
     * @return a list of users
     */
    public List<User> getRosterUsersForRoleAndEvent(long idEvent, long code);

}

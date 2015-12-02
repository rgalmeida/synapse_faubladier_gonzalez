package fr.synapsegaming.raid.service;

import java.util.List;

import fr.synapsegaming.raid.entity.Difficulty;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.entity.Raid;
import fr.synapsegaming.user.entity.User;

/**
 * <b>RaidService</b> is the public interface for Raid business logic
 * 
 * @author Meidi
 * 
 */
public interface RaidService {

    /**
     * List every raid in the current extension
     * 
     * @return list of Raids
     */
    public List<Raid> getRaidsForCurrentExtension();

    /**
     * Get a raid
     * 
     * @param idRaid
     *            : id of the raid
     * @return a Raid
     */
    public Raid getRaid(long idRaid);

    /**
     * List all of the difficulties
     * 
     * @return a list of Difficulty
     */
    public List<Difficulty> listDifficulties();

    /**
     * Get an event by id
     * 
     * @param id
     *            : technical identifier of the event
     * @return an Event
     */
    public Event getEvent(long id);

    /**
     * Get all the users who are TANKS subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by tanks subscription
     * @return a list of users
     */
    public List<User> getTanksForEvent(long idEvent);

    /**
     * Get all the users who are MELEES subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by melees subscription
     * @return a list of users
     */
    public List<User> getMeleesForEvent(long idEvent);

    /**
     * Get all the users who are RANGERS subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by rangers subscription
     * @return a list of users
     */
    public List<User> getRangersForEvent(long idEvent);

    /**
     * Get all the users who are HEALS subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by heals subscription
     * @return a list of users
     */
    public List<User> getHealsForEvent(long idEvent);

    /**
     * Get all the roster users who are TANKS subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by tanks subscription
     * @return a list of users
     */
    public List<User> getRosterTanksForEvent(long idEvent);

    /**
     * Get all the roster users who are MELEES subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by melees subscription
     * @return a list of users
     */
    public List<User> getRosterMeleesForEvent(long idEvent);

    /**
     * Get all the roster users who are RANGERS subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by rangers subscription
     * @return a list of users
     */
    public List<User> getRosterRangersForEvent(long idEvent);

    /**
     * Get all the roster users who are HEALS subscribing for this event
     * 
     * @param idEvent
     *            : the event concerned by heals subscription
     * @return a list of users
     */
    public List<User> getRosterHealsForEvent(long idEvent);

    /**
     * Subscribe a user to an event
     * 
     * @param user
     *            : the current user
     * @param idEvent
     *            : id of the event
     */
    public void subscribeEvent(User user, long idEvent);

    /**
     * Unsubscribe a user to an event
     * 
     * @param user
     *            : the current user
     * @param idEvent
     *            : id of the event
     */
    public void unsubscribeEvent(User user, long idEvent);

    /**
     * Add a user to the roster of an event
     * 
     * @param idUser
     *            : the id of the user to add
     * @param idEvent
     *            : the roster's event
     */
    public void addToEventRoster(long idUser, long idEvent);

    /**
     * Remove a user to the roster of an event
     * 
     * @param user
     *            : the id of the user to remove
     * @param idEvent
     *            : the roster's event
     */
    public void removeFromEventRoster(long idUser, long idEvent);

}

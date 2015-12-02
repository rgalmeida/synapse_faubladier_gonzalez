package fr.synapsegaming.raid.service;

import java.util.List;

import fr.synapsegaming.raid.entity.Event;

/**
 * <b>EventService</b> is the public interface for Event business logic
 * 
 * @author Meidi
 * 
 */
public interface EventService {

    /**
     * Return the list of Events in the current month
     * 
     * @return a list of events
     */
    public List<Event> getCurrentMonthEvents();

    /**
     * List all the events for the specified month and year
     * 
     * @param month
     *            : the event month
     * @param year
     *            : the event year
     * @return
     */
    public List<Event> getEvents(int month, int year);

    /**
     * Create a new event
     * 
     * @param eventForm
     *            : fields in event form
     */
    public void create(Event eventForm);

    /**
     * Send a mail to the entire guild to notify the event creation
     * 
     * @param event
     *            : the event to notify
     */
    public void sendInvitationMail(Event event);

}

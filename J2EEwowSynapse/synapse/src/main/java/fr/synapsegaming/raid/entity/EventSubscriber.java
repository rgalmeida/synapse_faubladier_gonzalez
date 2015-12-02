package fr.synapsegaming.raid.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.synapsegaming.user.entity.User;

/**
 * <b>EventUserAssociation</b> stands for the association between a user and its
 * events
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "r_events_users")
public class EventSubscriber {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event_user")
    private long id;

    /**
     * The event associated to a user
     */
    @ManyToOne
    @JoinColumn(name = "id_event", nullable = false, unique = true)
    private Event event;

    /**
     * The user associated to an event
     */
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User user;

    public EventSubscriber(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public EventSubscriber() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

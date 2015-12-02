package fr.synapsegaming.raid.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import fr.synapsegaming.user.entity.User;

/**
 * <b>Event</b> stands for a raid event which people can subscribe
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "r_events")
public class Event {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event")
    private long id;

    /**
     * The name of the event
     */
    @Column
    @NotNull
    @NotEmpty
    private String name;

    /**
     * The raid where the event takes place
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_raid")
    @NotNull
    @Valid
    private Raid raid;

    /**
     * The state of the Event
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event_state")
    @NotNull
    @Valid
    private EventState state;

    /**
     * People subscribing
     */
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Set<EventSubscriber> eventSubscribers;

    /**
     * Roster
     */
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private Set<EventRoster> roster;

    /**
     * The author of the event
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @NotNull
    private User creator;

    /**
     * The date when the event will take place
     */
    @Column
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date date;

    /**
     * The start time of the event
     */
    @Column(name = "time_start", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private Date startTime;

    /**
     * The stop time of the event
     */
    @Column(name = "time_stop", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull
    private Date stopTime;

    /**
     * The Difficulty of the raid event
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_difficulty")
    @NotNull
    @Valid
    private Difficulty difficulty;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Raid getRaid() {
        return raid;
    }

    public void setRaid(Raid raid) {
        this.raid = raid;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<EventSubscriber> getEventSubscribers() {
        return eventSubscribers;
    }

    public void setEventSubscribers(Set<EventSubscriber> eventSubscribers) {
        this.eventSubscribers = eventSubscribers;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<EventRoster> getRoster() {
        return roster;
    }

    public void setRoster(Set<EventRoster> roster) {
        this.roster = roster;
    }

}

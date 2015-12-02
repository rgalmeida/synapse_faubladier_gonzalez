package fr.synapsegaming.raid.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 * <b>EventState</b> stands for the state of an event. For example : "done" or
 * "coming"
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "r_events_states")
public class EventState {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_event_state")
    @Min(value = 1, message = "Champ obligatoire")
    private long id;

    /**
     * The name of the state
     */
    @Column
    private String name;

    /**
     * The list of events for this state
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_event")
    private List<Event> events;

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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}

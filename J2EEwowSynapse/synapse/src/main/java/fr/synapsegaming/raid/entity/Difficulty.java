package fr.synapsegaming.raid.entity;

import java.util.List;
import java.util.Set;

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
 * <b>Difficulty</b> stands for the different mods to play on a Boss
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "difficulties")
public class Difficulty {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_difficulty", unique = true, nullable = false)
    @Min(value = 1, message = "Champ obligatoire")
    private long id;

    /**
     * The name of the Difficulty
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * A list of bosses with this difficulty
     */
    @OneToMany(mappedBy = "difficulty")
    private List<Achievement> achievement;

    /**
     * The list of events with this difficulty
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_event")
    private Set<Event> events;

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

    public List<Achievement> getAchievement() {
        return achievement;
    }

    public void setAchievement(List<Achievement> achievement) {
        this.achievement = achievement;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

}

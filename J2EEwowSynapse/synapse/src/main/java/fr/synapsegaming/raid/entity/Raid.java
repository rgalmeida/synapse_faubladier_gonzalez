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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "raids")
public class Raid {

    /**
     * The technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_raid", unique = true, nullable = false)
    @Min(value = 1, message = "Champ obligatoire")
    private long id;

    /**
     * The name of the raid
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The URL of the image illustrating the raid
     */
    @Column(name = "imageUrl")
    private String imageUrl;

    /**
     * The raid's acronym
     */
    @Column(name = "acronym", length = 6)
    private String acronym;

    /**
     * The Patch where the raid is from
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patch")
    private Patch patch;

    /**
     * The list of bosses from the raid
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_raid")
    @OrderBy("boss DESC")
    private Set<Achievement> achievements;

    /**
     * The list of events for this raid
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raid")
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

    public Patch getPatch() {
        return patch;
    }

    public void setPatch(Patch patch) {
        this.patch = patch;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(Set<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

}

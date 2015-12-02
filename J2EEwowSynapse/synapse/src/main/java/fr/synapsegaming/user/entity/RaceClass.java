package fr.synapsegaming.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>RaceClass</b> is the association between Race and multiple classes
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "races_classes")
public class RaceClass {

    /**
     * Id of the association
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_race_class", unique = true, nullable = false)
    private long id;

    /**
     * Id of the race
     */
    @ManyToOne
    @JoinColumn(name = "id_race", updatable = false, insertable = false)
    private Race race;

    /**
     * Id of the clazz
     */
    @ManyToOne
    @JoinColumn(name = "id_class", updatable = false, insertable = false)
    private Clazz clazz;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

}

package fr.synapsegaming.user.entity;

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
 * <b>Race</b> stands for the different races of the guild faction (Horde)
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "races")
public class Race {

    /**
     * Technical unique identifier for a Race
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_race")
    @Min(value = 1, message = "Champ obligatoire")
    private long id;

    /**
     * The name of the Race
     */
    @Column(name = "name")
    private String name;

    /**
     * Match classes for a race
     */
    @OneToMany(mappedBy = "race")
    private List<RaceClass> raceClasses;

    /**
     * Users playing this race
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_race")
    private Set<User> users;

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

    public List<RaceClass> getRaceClasses() {
        return raceClasses;
    }

    public void setRaceClasses(List<RaceClass> raceClasses) {
        this.raceClasses = raceClasses;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}

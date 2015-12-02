package fr.synapsegaming.user.entity;

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
 * <b>Server</b> stands for the Blizzard Realms
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "realms")
public class Realm {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_realm", unique = true, nullable = false)
    @Min(value = 1, message = "Champ obligatoire")
    private int id;

    /**
     * The Blizard name of realm
     */
    @Column
    private String name;

    /**
     * Users from the Realm
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_realm")
    private Set<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}

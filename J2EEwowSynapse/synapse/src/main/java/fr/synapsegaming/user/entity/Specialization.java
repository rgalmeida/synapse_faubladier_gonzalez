package fr.synapsegaming.user.entity;

import java.util.List;

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
import javax.validation.constraints.Min;

/**
 * <b>Specialization</b> stands for the different specializations that can be
 * played for a race
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "specializations")
public class Specialization {

    /**
     * Technical unique identifier for a Spec
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spec")
    @Min(value = 1, message = "Champ obligatoire")
    private long id;

    /**
     * The name of the Spec
     */
    @Column(name = "name")
    private String name;

    /**
     * The path to the spec image
     */
    @Column(name = "img")
    private String img;

    @Column(name = "recruiting")
    private boolean recruiting;

    /**
     * The class where the spec is from
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_class")
    private Clazz clazz;

    /**
     * Users playing this specs
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_spec")
    private List<User> users;

    /**
     * Roles for this spec
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    private Role role;

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

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isRecruiting() {
        return recruiting;
    }

    public void setRecruiting(boolean recruiting) {
        this.recruiting = recruiting;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}

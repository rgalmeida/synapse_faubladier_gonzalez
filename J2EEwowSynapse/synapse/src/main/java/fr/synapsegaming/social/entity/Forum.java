package fr.synapsegaming.social.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import fr.synapsegaming.user.entity.Group;

/**
 * <b>Forum</b> stands for the different forums of the forum page
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "f_forum")
public class Forum {

    /**
     * Technical unique identifier for a category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forum")
    private long id;

    /**
     * The name of the Forum
     */
    @Column(name = "name")
    private String name;

    /**
     * A short description of this forum
     */
    @Column(name = "description", nullable = true)
    private String desc;

    /**
     * The list of groups seeing this forum
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "f_groups_forums", joinColumns = @JoinColumn(name = "id_forum"), inverseJoinColumns = @JoinColumn(name = "id_group"))
    private Set<Group> groups;

    /**
     * The list of categories for this forum
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_forum")
    @OrderBy("name ASC")
    private Set<ForumCategory> categories;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<ForumCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<ForumCategory> categories) {
        this.categories = categories;
    }

}

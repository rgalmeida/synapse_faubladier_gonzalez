package fr.synapsegaming.ui.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import fr.synapsegaming.user.entity.Group;

/**
 * <b>Resource</b> stands for a Resource used in a Menu
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "resources")
public class Resource {

    /**
     * Resource's technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resource", unique = true, nullable = false)
    private long id;

    /**
     * Resource's name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Resource's path to go on click
     */
    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "sort", nullable = false)
    private int order;

    /**
     * Groups owning the resource
     */
    @ManyToMany
    @JoinTable(name = "groups_resources", joinColumns = @JoinColumn(name = "id_resource"), inverseJoinColumns = @JoinColumn(name = "id_group"))
    private List<Group> groups;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
        
    }

}

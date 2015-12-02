package fr.synapsegaming.raid.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Boss</b> stands for the ennemies that the guild have to defeat
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "bosses")
public class Boss {

    /**
     * Technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boss", unique = true, nullable = false)
    private long id;

    /**
     * The Bosses name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Image to display for the boss
     */
    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    /**
     * The Boss mod : HM, Normal...
     */
    @OneToMany(mappedBy = "boss")
    private List<Achievement> achievements;

    /**
     * The order of appearance
     */
    @Column(name = "position", nullable = false)
    private int order;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

}

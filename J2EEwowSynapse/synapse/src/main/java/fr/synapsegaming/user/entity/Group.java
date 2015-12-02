package fr.synapsegaming.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import fr.synapsegaming.media.entity.Article;
import fr.synapsegaming.social.entity.Forum;
import fr.synapsegaming.ui.entity.Resource;

/**
 * <b>Group</b> stands for the user's group
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "groups")
public class Group {

    /**
     * Technical identifier of the group
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private int id;

    /**
     * Name of the group
     */
    @Column(name = "name")
    private String name;

    /**
     * The list of resources for this group
     */
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Resource> resources;

    /**
     * The list of articles for this group
     */
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Article> articles;

    /**
     * The list of forums for this group
     */
    @ManyToMany(mappedBy = "groups", fetch = FetchType.LAZY)
    private List<Forum> forums;

    public Group() {

    }

    public Group(int id) {
        this.id = id;
    }

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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Forum> getForums() {
        return forums;
    }

    public void setForums(List<Forum> forums) {
        this.forums = forums;
    }

}

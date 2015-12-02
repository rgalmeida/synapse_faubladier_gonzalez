package fr.synapsegaming.media.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.synapsegaming.user.entity.Group;
import fr.synapsegaming.user.entity.User;

/**
 * <b>Blog</b> stands for the blog's articles
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "articles")
@NamedQueries({ @NamedQuery(name = "Article.getLastArticlesByType", query = "SELECT a FROM Article a JOIN FETCH a.type t WHERE t.id = :type") })
public class Article {

    /**
     * Technical unique identifier for every article
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article", unique = true, nullable = false)
    private long id;

    /**
     * Title of the article
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * Short description of the article
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The content of the article
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * The user who made the article
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User author;

    /**
     * Date of creation for the article
     */
    @Column(name = "date_create", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateCreate;

    /**
     * Last update date for the article
     */
    @Column(name = "date_update", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateUpdate;

    /**
     * The type of article : news, blog...
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_article_type")
    private ArticleType type;

    /**
     * The covering image to display on the portal's header
     */
    @Column(name = "cover", nullable = true)
    private String coverImage;

    /**
     * The illustration of the article
     */
    @Column(name = "imageUrl", nullable = true)
    private String imageUrl;

    /**
     * The header image of the article
     */
    @Column(name = "imageHeader", nullable = false)
    private String imageHeader;

    /**
     * Groups seeing the article
     */
    @ManyToMany
    @JoinTable(name = "groups_articles", joinColumns = @JoinColumn(name = "id_article"), inverseJoinColumns = @JoinColumn(name = "id_group"))
    private List<Group> groups;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getImageHeader() {
        return imageHeader;
    }

    public void setImageHeader(String imageHeader) {
        this.imageHeader = imageHeader;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}

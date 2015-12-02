package fr.synapsegaming.social.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import fr.synapsegaming.user.entity.User;

/**
 * <b>ForumPost</b> stands for the different posts of a forum's category
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "f_posts")
public class ForumPost {

    /**
     * Post technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private long id;

    /**
     * The topic of the post
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The content of the post
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * Date of creation
     */
    @Column(name = "date_create", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateCreate;

    /**
     * Last date of edition
     */
    @Column(name = "date_update", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateUpdate;

    /**
     * Able / Unable replies on the post
     */
    @Column(name = "locked")
    private boolean locked;

    /**
     * The author of the post
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User author;

    /**
     * The list of replies for a post
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_post")
    @OrderBy("dateCreate ASC, dateUpdate ASC")
    private Set<ForumReply> replies;

    /**
     * The category of the post
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cat")
    private ForumCategory category;

    /**
     * The last user who updated the post
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_update")
    private User lastUpdateUser;

    public ForumPost() {

    }

    public ForumPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<ForumReply> getReplies() {
        return replies;
    }

    public void setReplies(Set<ForumReply> replies) {
        this.replies = replies;
    }

    public ForumCategory getCategory() {
        return category;
    }

    public void setCategory(ForumCategory category) {
        this.category = category;
    }

    public User getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(User lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public ForumReply getLastReply() {
        ForumReply lastReply = new ForumReply();
        for (ForumReply reply : this.getReplies()) {
            if (lastReply.getDateCreate() == null) {
                lastReply = reply;
            } else if (lastReply.getDateCreate().before(reply.getDateCreate())) {
                lastReply = reply;
            }
        }
        return lastReply;
    }
    


}

package fr.synapsegaming.social.entity;

import java.util.ArrayList;
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

/**
 * <b>ForumCategory</b> stands for the different categories of the forum
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "f_categories")
public class ForumCategory {

    /**
     * Technical unique identifier for a category
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat")
    private long id;

    /**
     * The name of the Category
     */
    @Column(name = "name")
    private String name;

    /**
     * A short description of this category
     */
    @Column(name = "description", nullable = true)
    private String desc;

    /**
     * The forum containing the category
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_forum")
    private Forum forum;

    /**
     * The list of posts for this category
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cat")
    @OrderBy("dateUpdate DESC, dateCreate DESC")
    private Set<ForumPost> posts;

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

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public Set<ForumPost> getPosts() {
        return posts;
    }

    public void setPosts(Set<ForumPost> posts) {
        this.posts = posts;
    }

    public ForumReply getLastReply() {
        List<ForumReply> lastReplies = new ArrayList<ForumReply>();
        for (ForumPost post : this.getPosts()) {
            lastReplies.add(post.getLastReply());
        }
        ForumReply lastReply = new ForumReply();
        for (ForumReply reply : lastReplies) {
            if (lastReply.getDateCreate() == null) {
                lastReply = reply;
            } else if (reply.getDateCreate() != null
                    && lastReply.getDateCreate().before(reply.getDateCreate())) {
                lastReply = reply;
            }
        }
        return lastReply;
    }

}

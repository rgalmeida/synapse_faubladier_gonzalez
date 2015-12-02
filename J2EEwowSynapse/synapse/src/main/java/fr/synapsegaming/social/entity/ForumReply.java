package fr.synapsegaming.social.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.synapsegaming.user.entity.User;

/**
 * <b>ForumReply</b> stands for the different reply of a forum's post
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "f_replies")
public class ForumReply {

    /**
     * Reply technical unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reply")
    private long id;

    /**
     * Content of the reply
     */
    @Column(name = "content")
    private String content;

    /**
     * Date of creation
     */
    @Column(name = "date_create", nullable = true)
    private Date dateCreate;

    /**
     * Date of the last edition
     */
    @Column(name = "date_update", nullable = true)
    private Date dateUpdate;

    /**
     * The author of the reply
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User author;

    /**
     * The original post for this reply
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_post")
    private ForumPost post;

    public ForumReply() {

    }

    public ForumReply(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public ForumPost getPost() {
        return post;
    }

    public void setPost(ForumPost post) {
        this.post = post;
    }

}

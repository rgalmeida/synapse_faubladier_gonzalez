package fr.synapsegaming.user.entity;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import fr.synapsegaming.media.entity.Article;
import fr.synapsegaming.raid.entity.EventRoster;
import fr.synapsegaming.raid.entity.EventSubscriber;
import fr.synapsegaming.social.entity.ForumPost;
import fr.synapsegaming.social.entity.ForumReply;

/**
 * <b>User</b> stands for the user's informations
 * 
 * @author Meidi
 * 
 */
@Entity
@Table(name = "users")
public class User {
	public static String USER_LINK_DEFAULT_AVATAR = "/resources/img/default_avatar.png";

	/**
     * Technical unique identifier of a User
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", unique = true, nullable = false)
    private long id;

    /**
     * The User's name
     */
    @Column(name = "name", nullable = true)
    private String name;

    /**
     * The User's surname
     */
    @Column(name = "surname", nullable = true)
    private String surname;

    /**
     * The User's date of birth
     */
    @Column(name = "birth", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Vous n'êtes pas né dans le futur :)")
    private Date birth;

    /**
     * The User's phone number
     */
    @Column(name="phone", nullable=true)
    private String phone;

    /**
     * The User's mail used to register and connect
     */
    @Column(name = "mail", nullable = false)
    @NotEmpty(message = "Champ obligatoire")
    @Email(message = "Format du mail invalide")
    private String mail;

    /**
     * The User's password used to register and connect
     */
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Champ obligatoire")
    private String password;

    /**
     * The list of articles of a User
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private Set<Article> articles;

    /**
     * The nickname of the main char of the User
     */
    @Column(name = "nickname", nullable = false)
    @NotEmpty(message = "Champ obligatoire")
    private String nickname;

    /**
     * The race of the main char of the User
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_race")
    @NotNull
    @Valid
    private Race race;

    /**
     * The class of the main char of the User
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_class")
    @NotNull
    @Valid
    private Clazz clazz;

    /**
     * The main spec of_user the main char of the User
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_spec")
    @NotNull
    @Valid
    private Specialization spec;

    /**
     * True if the user has been activated. False if he's not active.
     */
    @Column(name = "active", nullable = false)
    private boolean active;

    /**
     * The user's group
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_group")
    private Group group;

    /**
     * Tells if the user accepted the legals
     */
    @Column(name = "legals")
    @AssertTrue(message = "* Pour vous inscrire vous devez accepter les conditions d'utilisation")
    private boolean legalsAccepted;

    /**
     * The list of posts in the forum
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post")
    private List<ForumPost> posts;

    /**
     * The list of replies in the forum
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private List<ForumReply> replies;

    /**
     * The list of events where the user has subscribed
     */
    @OneToMany(mappedBy = "user")
    private Set<EventSubscriber> eventSubscribers;

    /**
     * Roster
     */
    @OneToMany(mappedBy = "user")
    private Set<EventRoster> roster;

    /**
     * User's Blizzard server
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_realm")
    @NotNull
    @Valid
    private Realm realm;

    /**
     * The user's avatar on the forum
     */
    @Column(name = "avatar_forum")
    private String forumAvatar;

    /**
     * The list of posts updated in the forum
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_post")
    private List<ForumPost> updatedPosts;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Specialization getSpec() {
        return spec;
    }

    public void setSpec(Specialization spec) {
        this.spec = spec;
    }

    public boolean isLegalsAccepted() {
        return legalsAccepted;
    }

    public void setLegalsAccepted(boolean legalsAccepted) {
        this.legalsAccepted = legalsAccepted;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<ForumPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ForumPost> posts) {
        this.posts = posts;
    }

    public List<ForumReply> getReplies() {
        return replies;
    }

    public void setReplies(List<ForumReply> replies) {
        this.replies = replies;
    }

    public Set<EventSubscriber> getEventSubscribers() {
        return eventSubscribers;
    }

    public void setEventSubscribers(Set<EventSubscriber> eventSubscribers) {
        this.eventSubscribers = eventSubscribers;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public String getForumAvatar() {
        return forumAvatar;
    }

    public void setForumAvatar(String forumAvatar) {
        this.forumAvatar = forumAvatar;
    }

    public List<ForumPost> getUpdatedPosts() {
        return updatedPosts;
    }

    public void setUpdatedPosts(List<ForumPost> updatedPosts) {
        this.updatedPosts = updatedPosts;
    }

    public Set<EventRoster> getRoster() {
        return roster;
    }

    public void setRoster(Set<EventRoster> roster) {
        this.roster = roster;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

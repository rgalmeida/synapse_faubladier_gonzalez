package fr.synapsegaming.social.service;

import java.util.List;

import fr.synapsegaming.social.entity.Forum;
import fr.synapsegaming.social.entity.ForumCategory;
import fr.synapsegaming.social.entity.ForumPost;
import fr.synapsegaming.social.entity.ForumReply;
import fr.synapsegaming.user.entity.User;

/**
 * <b>ForumService</b> is the public interface for Forum business logic
 * 
 * @author Meidi
 * 
 */
public interface ForumService {

    /**
     * List all of the categories visible for a user
     * 
     * @param user
     *            : the current user
     * @return
     */
    public List<Forum> list(User user);

    /**
     * Get a forum category by id
     * 
     * @param id
     *            : identifier of the category
     * @param user
     *            : the current user
     * @return a category
     */
    public ForumCategory getCategory(long id, User user);

    /**
     * Get a forum category post by id
     * 
     * @param id
     *            : identifier of the post
     * @param user
     *            : the current user
     * @return a post
     */
    public ForumPost getPost(long id, User user);

    /**
     * Add a reply to a post
     * 
     * @param idPost
     *            : technical identifier for the post
     * @param user
     *            : the author of the reply
     * @param reply
     *            : the reply to the post
     */
    public void addReply(long idPost, User user, ForumReply reply);

    /**
     * Add a new post to a category
     * 
     * @param idCategory
     *            : the unique identifier of the category
     * @param user
     *            : the author of the post
     * @param post
     *            : the post to add
     * @return the id of the new post
     */
    public long addPost(long idCategory, User user, ForumPost post);

    /**
     * Delete a reply
     * 
     * @param idReply
     *            : the id of the reply to delete
     */
    public void deleteReply(long idReply);

    /**
     * Edit a post in the hour of creation if the values have changed
     * 
     * @param idPost
     *            : the post to edit
     * @param user
     *            : the user who's editing the post
     * @param forumPost
     *            : the new values of the post to edit
     */
    public void editPost(long idPost, User user, ForumPost post);

}

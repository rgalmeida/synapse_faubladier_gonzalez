package fr.synapsegaming.social.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.social.dao.ForumCategoryDao;
import fr.synapsegaming.social.dao.ForumDao;
import fr.synapsegaming.social.dao.ForumPostDao;
import fr.synapsegaming.social.dao.ForumReplyDao;
import fr.synapsegaming.social.entity.Forum;
import fr.synapsegaming.social.entity.ForumCategory;
import fr.synapsegaming.social.entity.ForumPost;
import fr.synapsegaming.social.entity.ForumReply;
import fr.synapsegaming.social.service.ForumService;
import fr.synapsegaming.user.entity.Group;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.enums.GroupEnum;

@Service("ForumService")
@Transactional
public class ForumServiceImpl implements ForumService {

    @Autowired
    ForumDao forumDao;

    @Autowired
    ForumCategoryDao forumCategoryDao;

    @Autowired
    ForumPostDao forumPostDao;

    @Autowired
    ForumReplyDao forumReplyDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Forum> list(User user) {
        if (user != null && user.getId() > 0) {
            return forumDao.list(user.getGroup().getId());
        } else {
            return forumDao.list(GroupEnum.GUEST.getCode());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ForumCategory getCategory(long id, User user) {
        ForumCategory category = forumCategoryDao.find(ForumCategory.class, id);
        if (user != null && user.getId() > 0
                && isUserGroupAuthorized(user, category.getForum().getGroups())) {
            return category;
        } else if (user == null || user.getId() == 0) {
            return category;
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ForumPost getPost(long id, User user) {
        ForumPost post = forumPostDao.find(ForumPost.class, id);
        if (user != null
                && user.getId() > 0
                && isUserGroupAuthorized(user, post.getCategory().getForum()
                        .getGroups())) {
            return post;
        } else if (user == null || user.getId() == 0) {
            return post;
        } else {
            return null;
        }
    }

    /**
     * Check if a user got the needed rights
     * 
     * @param user
     *            : the current user
     * @param groups
     *            : the groups authorized
     * @return <b>TRUE</b> : if the user got the rights | <b>FALSE</b> : on the
     *         contrary
     */
    private boolean isUserGroupAuthorized(User user, Set<Group> groups) {
        boolean isUserGroupAuthorized = false;
        for (Group group : groups) {
            if (group.getId() == user.getGroup().getId()) {
                isUserGroupAuthorized = true;
            }
        }
        return isUserGroupAuthorized;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addReply(long idPost, User user, ForumReply reply) {
        if (user != null && user.getId() > 0) {
            ForumPost post = forumPostDao.find(ForumPost.class, idPost);
            reply.setAuthor(user);
            reply.setDateCreate(new Date());
            reply.setPost(post);
            forumReplyDao.save(reply);
            post.setDateUpdate(new Date());
            post.setLastUpdateUser(user);
            forumPostDao.update(post);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long addPost(long idCategory, User user, ForumPost post) {
        if (user != null && user.getId() > 0) {
            ForumCategory category = forumCategoryDao.find(ForumCategory.class,
                    idCategory);
            post.setAuthor(user);
            post.setCategory(category);
            post.setDateCreate(new Date());
            post.setDateUpdate(new Date());
            post.setLastUpdateUser(user);
            return forumPostDao.save(post);
        }
        return 0L;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteReply(long idReply) {
        forumReplyDao.delete(forumReplyDao.find(ForumReply.class, idReply));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editPost(long idPost, User user, ForumPost post) {
        if (user != null && user.getId() > 0) {
            ForumPost postToEdit = forumPostDao.find(ForumPost.class, idPost);
            postToEdit.setDateUpdate(new Date());
            postToEdit.setLastUpdateUser(user);
            postToEdit.setTitle(post.getTitle());
            postToEdit.setContent(post.getContent());
            forumPostDao.update(postToEdit);
        }
    }

}

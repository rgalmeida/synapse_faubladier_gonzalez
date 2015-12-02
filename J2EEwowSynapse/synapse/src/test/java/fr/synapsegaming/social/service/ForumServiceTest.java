package fr.synapsegaming.social.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;
import fr.synapsegaming.social.entity.ForumPost;
import fr.synapsegaming.social.entity.ForumReply;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.entity.Group;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.enums.GroupEnum;

public class ForumServiceTest extends AbstractServiceTest {
	
	private static final long DATABASE_USER_ID = 1L;
	private static final long CATEGORY_ID = 1L;
	private static final long POST_ID = 1L;
	private static final String REPLY_CONTENT ="Content for reply";
	private static final User FORUM_USER = new User();
	private static final ForumPost FORUM_POST_FOR_REPLY = new ForumPost();
	private static final ForumReply FORUM_REPLY = new ForumReply();
	
	@Autowired
	ForumService forumService;
	
	@Autowired
	UserDao userDao;
	
	@BeforeClass
	public static void setUpClass(){
		FORUM_USER.setGroup(new Group(GroupEnum.WEBMASTER.getCode()));		
		FORUM_POST_FOR_REPLY.setId(POST_ID);
		FORUM_REPLY.setAuthor(FORUM_USER);
		FORUM_REPLY.setContent(REPLY_CONTENT);
		FORUM_REPLY.setDateCreate(new Date());
		FORUM_REPLY.setPost(FORUM_POST_FOR_REPLY);
	}

	@Test
	public void testList() {
		assertTrue(CollectionUtils.isNotEmpty(forumService.list(FORUM_USER)));
	}

	@Test
	public void testGetCategory() {
		assertTrue(forumService.getCategory(CATEGORY_ID, FORUM_USER)!=null);
	}

	@Test
	public void testGetPost() {
		assertTrue(forumService.getPost(POST_ID, FORUM_USER)!=null);
	}

	@Test
	public void testAddReply() {
		forumService.addReply(POST_ID, userDao.find(User.class, DATABASE_USER_ID), FORUM_REPLY);
		assertTrue(FORUM_REPLY.getId() > 0);
	}

}

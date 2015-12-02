package fr.synapsegaming.social.dao;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.commons.dao.AbstractDaoTest;
import fr.synapsegaming.social.entity.ForumReply;

public class ForumReplyDaoTest extends AbstractDaoTest {
	@Autowired
	ForumReplyDao forumReply;
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUsersActivityScores()
	{
		assertTrue(CollectionUtils.isNotEmpty(forumReply.listUsersActivityScore(null, null, 0)));
	}
	
	@Test
	public void testGetAllReplies()
	{
		assertTrue(CollectionUtils.isNotEmpty(forumReply.list(ForumReply.class)));
	}

}

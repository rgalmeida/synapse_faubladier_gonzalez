package fr.synapsegaming.social.dao;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;
public class ForumPostDaoTest extends AbstractDaoTest{
	@Autowired
	ForumPostDao forumPost;
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	/*
	 * Exercise
	 */
	@Test 
	public void testListUsersActivityScore()
	{
		assertTrue(CollectionUtils.isNotEmpty(forumPost.listUsersActivityScore(null, null, 0)));
	}

}

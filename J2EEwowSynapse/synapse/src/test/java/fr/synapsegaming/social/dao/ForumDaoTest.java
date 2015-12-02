package fr.synapsegaming.social.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;
import fr.synapsegaming.user.enums.GroupEnum;

public class ForumDaoTest extends AbstractDaoTest {

	private static final String FIRST_FORUM_NAME_LISTED = "A lire";
	private static final int NB_FORUMS_FOR_ADMIN = 3;
	private static final int NB_FORUMS_FOR_GUEST = 1;

	@Autowired
	ForumDao forumDao;

	@Test
	public void testListForAdmin() {
		assertTrue(forumDao.list(GroupEnum.ADMIN.getCode()).size() == NB_FORUMS_FOR_ADMIN);
	}

	@Test
	public void testListForGuest() {
		assertTrue(forumDao.list(GroupEnum.GUEST.getCode()).size() == NB_FORUMS_FOR_GUEST);
	}

	@Test
	public void testListIsOrderedByForumName() {
		assertTrue(forumDao.list(GroupEnum.ADMIN.getCode()).get(0).getName().equals(FIRST_FORUM_NAME_LISTED));
	}
	


}

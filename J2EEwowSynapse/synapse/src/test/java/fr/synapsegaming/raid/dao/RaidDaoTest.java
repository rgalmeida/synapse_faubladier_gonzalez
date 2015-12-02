package fr.synapsegaming.raid.dao;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;
import fr.synapsegaming.raid.entity.Extension;

public class RaidDaoTest extends AbstractDaoTest {
	
	private static final Extension VALID_EXTENSION = new Extension();
	private static final int VALID_EXTENSION_ID = 1;
	
	@Before
	public void setUp() {
		VALID_EXTENSION.setId(VALID_EXTENSION_ID);
	}
	
	@Autowired
	RaidDao raidDao;

	@Test
	public void testListForValidExtension() {
		assertTrue(CollectionUtils.isNotEmpty(raidDao.listForExtension(VALID_EXTENSION)));
	}

}

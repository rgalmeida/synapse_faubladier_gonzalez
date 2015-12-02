package fr.synapsegaming.raid.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;

public class PatchDaoTest extends AbstractDaoTest {
	
	private static final String LAST_PATCH_NAME = "Blackrock Foundry";
	
	@Autowired
	PatchDao patchDao;

	@Test
	public void testGetLastPatch() {
		assertTrue(patchDao.getLastPatch().getName().equals(LAST_PATCH_NAME));
	}
}

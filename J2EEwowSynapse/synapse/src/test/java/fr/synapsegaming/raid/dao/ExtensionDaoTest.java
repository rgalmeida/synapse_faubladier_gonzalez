package fr.synapsegaming.raid.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;

public class ExtensionDaoTest extends AbstractDaoTest {
	
	private static final String LAST_EXTENSION_NAME = "Warlords of Draenor";
	
	@Autowired
	ExtensionDao extensionDao;

	@Test
	public void testGetLastExtension() {
		assertTrue(extensionDao.getLastExtension().getName().equals(LAST_EXTENSION_NAME));
	}

}

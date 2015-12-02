package fr.synapsegaming.user.service;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;

public class RealmServiceTest extends AbstractServiceTest {
	
	private static final int REALM_ID = 1;
	
	@Autowired
	RealmService realmService;

	@Test
	public void testList() {
		assertTrue(CollectionUtils.isNotEmpty(realmService.list()));
	}

	@Test
	public void testFind() {
		assertTrue(realmService.find(REALM_ID) != null);
	}

}

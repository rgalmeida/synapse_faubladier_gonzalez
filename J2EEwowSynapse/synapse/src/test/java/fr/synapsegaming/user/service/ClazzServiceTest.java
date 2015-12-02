package fr.synapsegaming.user.service;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;

public class ClazzServiceTest extends AbstractServiceTest {
	
	private static final long RACE_ID = 1L;
	
	@Autowired
	ClazzService clazzService;

	@Test
	public void testList() {
		assertTrue(CollectionUtils.isNotEmpty(clazzService.list()));
	}
	//testing class
	@Test
	public void testListClassForRace() {
		assertTrue(CollectionUtils.isNotEmpty(clazzService.listClassForRace(RACE_ID)));
	}

}

package fr.synapsegaming.user.service;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;

public class RaceServiceTest extends AbstractServiceTest {
	
	@Autowired
	RaceService raceService;

	@Test
	public void testList() {
		assertTrue(CollectionUtils.isNotEmpty(raceService.list()));
	}

}

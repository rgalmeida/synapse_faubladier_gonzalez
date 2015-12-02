package fr.synapsegaming.user.service;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;

public class SpecializationServiceTest extends AbstractServiceTest {
	
	private static final long CLAZZ_ID = 1L;
	
	@Autowired
	SpecializationService specializationService;

	@Test
	public void testList() {
		assertTrue(CollectionUtils.isNotEmpty(specializationService.list()));
	}

	@Test
	public void testListSpecsForClass() {
		assertTrue(CollectionUtils.isNotEmpty(specializationService.listSpecsForClass(CLAZZ_ID)));
	}

}

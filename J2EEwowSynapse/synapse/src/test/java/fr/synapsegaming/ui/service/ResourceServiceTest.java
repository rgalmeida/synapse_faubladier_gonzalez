package fr.synapsegaming.ui.service;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;
import fr.synapsegaming.user.enums.GroupEnum;

public class ResourceServiceTest extends AbstractServiceTest {
	
	@Autowired
	ResourceService resourceService;

	@Test
	public void testList() {
		assertTrue(CollectionUtils.isNotEmpty(resourceService.list()));
	}

	@Test
	public void testListMainMenu() {
		assertTrue(CollectionUtils.isNotEmpty(resourceService.listMainMenu()));
	}

	@Test
	public void testListUserResources() {
		assertTrue(CollectionUtils.isNotEmpty(resourceService.listUserResources(GroupEnum.ADMIN.getCode())));
	}

	@Test
	public void testListUserRestrictedResources() {
		assertTrue(CollectionUtils.isNotEmpty(resourceService.listUserRestrictedResources(GroupEnum.ADMIN.getCode())));
	}

}

package fr.synapsegaming.ui.dao;

import static org.junit.Assert.assertTrue;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;
import fr.synapsegaming.user.enums.GroupEnum;

public class ResourceDaoTest extends AbstractDaoTest {

    private static final int UNEXISTING_GROUP_ID = 12;

    @Autowired
    ResourceDao resourceDao;

    @Test
    public void testListResourcesForGroupGuild() {
        assertTrue(CollectionUtils.isNotEmpty(resourceDao.listResourcesForGroup(GroupEnum.GUILD.getCode())));
    }

    @Test
    public void testListResourcesForGroupWebmaster() {
        assertTrue(CollectionUtils.isNotEmpty(resourceDao.listResourcesForGroup(GroupEnum.WEBMASTER.getCode())));
    }

    @Test
    public void testListResourcesForGroupAdmin() {
        assertTrue(CollectionUtils.isNotEmpty(resourceDao.listResourcesForGroup(GroupEnum.ADMIN.getCode())));
    }

    @Test
    public void testListResourcesForGroupGuest() {
        assertTrue(CollectionUtils.isNotEmpty(resourceDao.listResourcesForGroup(GroupEnum.GUEST.getCode())));
    }

    @Test
    public void testListNoResourcesForUnknownGroup() {
        assertTrue(CollectionUtils.isEmpty(resourceDao.listResourcesForGroup(UNEXISTING_GROUP_ID)));
    }

}

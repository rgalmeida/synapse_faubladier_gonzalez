package fr.synapsegaming.user.dao;

import static org.junit.Assert.assertTrue;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;

public class ClazzDaoTest extends AbstractDaoTest {

    private static final int EXISTING_RACE_ID = 1;
    private static final int UNEXISTING_RACE_ID = 99;

    @Autowired
    ClazzDao clazzDao;

    @Test
    public void testListClassForExistingRace() {
        assertTrue(CollectionUtils.isNotEmpty(clazzDao.listClassForRace(EXISTING_RACE_ID)));
    }

    @Test
    public void testListClassForNonExistingRace() {
        assertTrue(CollectionUtils.isEmpty(clazzDao.listClassForRace(UNEXISTING_RACE_ID)));
    }

}

package fr.synapsegaming.user.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;

public class RealmDaoTest extends AbstractDaoTest {

    private static final String LIST_FIRST_EXPECTED_REALM_NAME = "Eitrigg";
    private static final int REALM_LIST_FIRST_ELEMENT_INDEX = 0;

    @Autowired
    RealmDao realmDao;

    @Test
    public void testListRealmsIsOrderByName() {
        assertTrue(realmDao.listRealmsOrderByName().get(REALM_LIST_FIRST_ELEMENT_INDEX).getName().equals(LIST_FIRST_EXPECTED_REALM_NAME));
    }

}

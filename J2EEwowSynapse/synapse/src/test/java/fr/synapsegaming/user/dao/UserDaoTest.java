package fr.synapsegaming.user.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;

public class UserDaoTest extends AbstractDaoTest {

    private static final String EXISTING_MAIL = "meidi.airouche@gmail.com";
    private static final String UNEXISTING_MAIL = "meidi@gmail.com";
    private static final int ADMIN_GROUP_ID = 1;
    private static final int WEBMASTER_GROUP_ID = 2;
    private static final int UNEXISTING_GROUP_ID = 99;
    private static final List<Integer> EXISTING_GROUP_IDS = new ArrayList<Integer>();
    private static final List<Integer> UNEXISTING_GROUP_IDS = new ArrayList<Integer>();
    private static final int IUT_TEST_DEFAULT_NUMBER_OF_ITEMS = 5;
    @Before
    public void setUp() {
        EXISTING_GROUP_IDS.add(ADMIN_GROUP_ID);
        EXISTING_GROUP_IDS.add(WEBMASTER_GROUP_ID);
        UNEXISTING_GROUP_IDS.add(UNEXISTING_GROUP_ID);
    }

    @Autowired
    UserDao userDao;

    @Test
    public void testFindWithExistingMail() {
        assertTrue(userDao.findByMail(EXISTING_MAIL).getName() != null);
    }

    @Test
    public void testFindWithUnexistingMail() {
        assertTrue(userDao.findByMail(UNEXISTING_MAIL) == null);
    }

    @Test
    public void testListUsersByExistingGroups() {
        assertTrue(CollectionUtils.isNotEmpty(userDao.listByGroups(EXISTING_GROUP_IDS)));
    }

    @Test
    public void testListUsersByUnexistingGroups() {
        assertTrue(CollectionUtils.isEmpty(userDao.listByGroups(UNEXISTING_GROUP_IDS)));
    }
    
    @Test
    public void testOrderedListByFieldRepetition(){
    	assertTrue(CollectionUtils.isNotEmpty(userDao.orderedListByFieldRepetition("User", "clazz.id")));
    }
    
    
    /*
     * iut exercises tests 
     */
    @Test
    public void testListTopUsedByType()
    {
    	//even if it was not used, it was prepared to make the query for some single field and simplify the lines of code...
    	assertTrue(CollectionUtils.isNotEmpty(userDao.listTopUsedbyType("group",IUT_TEST_DEFAULT_NUMBER_OF_ITEMS)));
 
    }
    @Test
    public void testListTopClassPlayed(){
    	assertTrue(CollectionUtils.isNotEmpty(userDao.listTopClassPlayed(IUT_TEST_DEFAULT_NUMBER_OF_ITEMS)));
    }
    @Test
    public void testListTopSpecializationsPlayed()
    {
    	assertTrue(CollectionUtils.isNotEmpty(userDao.listTopSpecializationsPlayed(IUT_TEST_DEFAULT_NUMBER_OF_ITEMS)));
    }
    
    @Test
    public void testListTopRacesPlayed()
    {
    	assertTrue(CollectionUtils.isNotEmpty(userDao.listTopRacesPlayed(IUT_TEST_DEFAULT_NUMBER_OF_ITEMS)));
    }
    
    @Test
    public void testListUsersWithDefaultAvatar()
    {
    	assertTrue(CollectionUtils.isNotEmpty(userDao.listUsersWithDefaultAvatar()));
    }
    
}

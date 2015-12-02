package fr.synapsegaming.user.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.entity.Clazz;
import fr.synapsegaming.user.entity.Group;
import fr.synapsegaming.user.entity.Race;
import fr.synapsegaming.user.entity.Realm;
import fr.synapsegaming.user.entity.Specialization;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.enums.GroupEnum;
import fr.synapsegaming.utils.CipherUtils;

public class UserServiceTest extends AbstractServiceTest {

    private static final String USER_NICKNAME_TO_UPDATE = "UpdatedNickname";
    private static final String USER_NAME_TO_UPDATE = "UpdatedName";
    private static final String USER_SURNAME_TO_UPDATE = "UpdatedSurname";
    private static final String PHONE_TO_UPDATE = "06 45 78 96 52";
    private static final long DATABASE_USER_ID = 1L;
    private static final String UPDATED_AVATAR = "avatar-updated.png";
    private static final String EMPTY_STRING = "";
    private static final String INACTIVE_USER_SIGNIN_MESSAGE = "Cet utilisateur n'a pas encore validé son inscription";
    private static final String WRONG_PASSWORD_USER_SIGNIN_MESSAGE = "Le mot de passe est erroné.";
    private static final long SPEC_ID = 1L;
    private static final long SPEC_TO_UPDATE_ID = 2L;
    private static final int REALM_TO_UPDATE_ID = 2;
    private static final int REALM_ID = 1;
    private static final long RACE_ID = 1L;
    private static final long CLAZZ_ID = 1L;
    private static final long USER_TO_FIND_ID = 1L;
    private static final long USER_WITH_AVATAR_TO_FIND_ID = 2L;
    private static final long DATABASE_UPDATE_TEST_USER_ID = 4L;
    private static final String DEFAULT_FORUM_AVATAR_PATH = "/resources/img/default_avatar.png";
    private static final int USER_ID_WHEN_NOT_IN_DATABASE = 0;
    private static final String BIRTH_DATE_STRING = "13/11/1988";
    private static final Date BIRTH_TO_UPDATE = new Date();
    private static final String USER_EMAIL = "test@test.fr";
    private static final String USER_EMAIL_TO_UPDATE = "UpdatedEmail@test.fr";
    private static final String USER_EMAIL_WITH_AVATAR = "xtremz@test.fr";
    private static final String USER_NAME = "TestName";
    private static final String USER_SURNAME = "TestSurname";
    private static final String USER_NICKNAME = "Testing";
    private static final String USER_NICKNAME_WITH_AVATAR = "Xtremz";
    private static final String USER_PASSWORD = "Test13.";
    private static final String USER_WRONG_PASSWORD = "";
    private static final String DATABASE_USER_MAIL = "meidi.airouche@gmail.com";
    private static final String DATABASE_INACTIVE_USER_MAIL = "inactive@test.fr";
    private static final String UNEXISTING_EMAIL = "t@t.fr";
    private static final User USER_SUBSCRIBING = new User();
    private static final User USER_SUBSCRIBING_WITH_AVATAR = new User();
    private static final User USER_SUBSCRIBING_ALREADY_EXISTING = new User();
    private static final User USER_SIGNIN = new User();
    private static final User USER_SIGNIN_INACTIVE = new User();
    private static final Clazz CLAZZ = new Clazz();
    private static final Race RACE = new Race();
    private static final Realm REALM = new Realm();
    private static final Realm REALM_TO_UPDATE = new Realm();
    private static final Specialization SPEC = new Specialization();
    private static final Specialization SPEC_TO_UPDATE = new Specialization();
    private static Date birthDate;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @BeforeClass
    public static void setUpClass() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        birthDate = formatter.parse(BIRTH_DATE_STRING);
        CLAZZ.setId(CLAZZ_ID);
        RACE.setId(RACE_ID);
        REALM.setId(REALM_ID);
        SPEC.setId(SPEC_ID);
        SPEC_TO_UPDATE.setId(SPEC_TO_UPDATE_ID);
        REALM_TO_UPDATE.setId(REALM_TO_UPDATE_ID);

        USER_SUBSCRIBING.setActive(false);
        USER_SUBSCRIBING.setBirth(birthDate);
        USER_SUBSCRIBING.setClazz(CLAZZ);
        USER_SUBSCRIBING.setGroup(new Group(GroupEnum.WEBMASTER.getCode()));
        USER_SUBSCRIBING.setLegalsAccepted(true);
        USER_SUBSCRIBING.setMail(USER_EMAIL);
        USER_SUBSCRIBING.setName(USER_NAME);
        USER_SUBSCRIBING.setNickname(USER_NICKNAME);
        USER_SUBSCRIBING.setPassword(USER_PASSWORD);
        USER_SUBSCRIBING.setRace(RACE);
        USER_SUBSCRIBING.setRealm(REALM);
        USER_SUBSCRIBING.setSpec(SPEC);
        USER_SUBSCRIBING.setSurname(USER_SURNAME);

        USER_SUBSCRIBING_WITH_AVATAR.setActive(false);
        USER_SUBSCRIBING_WITH_AVATAR.setBirth(birthDate);
        USER_SUBSCRIBING_WITH_AVATAR.setClazz(CLAZZ);
        USER_SUBSCRIBING_WITH_AVATAR.setGroup(new Group(GroupEnum.WEBMASTER.getCode()));
        USER_SUBSCRIBING_WITH_AVATAR.setLegalsAccepted(true);
        USER_SUBSCRIBING_WITH_AVATAR.setMail(USER_EMAIL_WITH_AVATAR);
        USER_SUBSCRIBING_WITH_AVATAR.setName(USER_NAME);
        USER_SUBSCRIBING_WITH_AVATAR.setNickname(USER_NICKNAME_WITH_AVATAR);
        USER_SUBSCRIBING_WITH_AVATAR.setPassword(USER_PASSWORD);
        USER_SUBSCRIBING_WITH_AVATAR.setRace(RACE);
        USER_SUBSCRIBING_WITH_AVATAR.setRealm(REALM);
        USER_SUBSCRIBING_WITH_AVATAR.setSpec(SPEC);
        USER_SUBSCRIBING_WITH_AVATAR.setSurname(USER_SURNAME);

        USER_SUBSCRIBING_ALREADY_EXISTING.setActive(false);
        USER_SUBSCRIBING_ALREADY_EXISTING.setBirth(birthDate);
        USER_SUBSCRIBING_ALREADY_EXISTING.setClazz(CLAZZ);
        USER_SUBSCRIBING_ALREADY_EXISTING.setGroup(new Group(GroupEnum.WEBMASTER.getCode()));
        USER_SUBSCRIBING_ALREADY_EXISTING.setLegalsAccepted(true);
        USER_SUBSCRIBING_ALREADY_EXISTING.setMail(DATABASE_USER_MAIL);
        USER_SUBSCRIBING_ALREADY_EXISTING.setName(USER_NAME);
        USER_SUBSCRIBING_ALREADY_EXISTING.setNickname(USER_NICKNAME_WITH_AVATAR);
        USER_SUBSCRIBING_ALREADY_EXISTING.setPassword(USER_PASSWORD);
        USER_SUBSCRIBING_ALREADY_EXISTING.setRace(RACE);
        USER_SUBSCRIBING_ALREADY_EXISTING.setRealm(REALM);
        USER_SUBSCRIBING_ALREADY_EXISTING.setSpec(SPEC);
        USER_SUBSCRIBING_ALREADY_EXISTING.setSurname(USER_SURNAME);

        USER_SIGNIN.setMail(DATABASE_USER_MAIL);
        USER_SIGNIN.setActive(true);
        USER_SIGNIN.setPassword(CipherUtils.encrypt(USER_PASSWORD));

        USER_SIGNIN_INACTIVE.setMail(DATABASE_INACTIVE_USER_MAIL);
        USER_SIGNIN_INACTIVE.setActive(false);
        USER_SIGNIN_INACTIVE.setPassword(CipherUtils.encrypt(USER_PASSWORD));
    }

    @Test
    public void testSubscribeUser() {
        assertTrue(userService.subscribe(USER_SUBSCRIBING).getId() > USER_ID_WHEN_NOT_IN_DATABASE);
    }

    @Test
    public void testSubscribeUserWithExistingEmail() {
        assertNull(userService.subscribe(USER_SUBSCRIBING_ALREADY_EXISTING));
    }

    @Test
    public void testUpdateUserAvatarFromBlizzard() {
        User user = userDao.find(User.class, USER_WITH_AVATAR_TO_FIND_ID);
        userService.updateUserAvatarFromBlizzard(user, user.getRealm());
        assertFalse(user.getForumAvatar().equals(DEFAULT_FORUM_AVATAR_PATH));
    }

    @Test
    public void testSubscribeUserWithAvatar() {
        assertTrue(userService.subscribe(USER_SUBSCRIBING_WITH_AVATAR).getId() > USER_ID_WHEN_NOT_IN_DATABASE);
    }

    @Test
    public void testEmailExist() {
        assertTrue(userService.emailExist(DATABASE_USER_MAIL));
    }

    @Test
    public void testFindByExistingMail() {
        assertTrue(userService.findByMail(DATABASE_USER_MAIL) != null);
    }

    @Test
    public void testFindByUnexistingMail() {
        assertTrue(userService.findByMail(UNEXISTING_EMAIL) == null);
    }

    @Test
    public void testUpdate() {
        User userToUpdate = userDao.find(User.class, DATABASE_USER_ID);
        userToUpdate.setForumAvatar(UPDATED_AVATAR);
        assertTrue(userToUpdate.getForumAvatar().equals(UPDATED_AVATAR));
    }

    @Test
    public void testFindUser() {
        assertTrue(userService.find(USER_TO_FIND_ID) != null);
    }

    @Test
    public void testGetAllUsers() {
        assertTrue(CollectionUtils.isNotEmpty(userService.getAllUsers()));
    }

    @Test
    public void testUserCanSignin() {
        assertTrue(userService.userCanSignin(USER_PASSWORD, USER_SIGNIN).equals(EMPTY_STRING));
    }

    @Test
    public void testUserCantSigninWithWrongPassword() {
        assertTrue(userService.userCanSignin(USER_WRONG_PASSWORD, USER_SIGNIN).equals(WRONG_PASSWORD_USER_SIGNIN_MESSAGE));
    }

    @Test
    public void testUserCantSigninWithoutBeingActive() {
        assertTrue(userService.userCanSignin(USER_PASSWORD, USER_SIGNIN_INACTIVE).equals(INACTIVE_USER_SIGNIN_MESSAGE));
    }

    @Test
    public void testUserUpdateNickname() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        user.setNickname(USER_NICKNAME_TO_UPDATE);
        userService.update(user);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getNickname().equals(USER_NICKNAME_TO_UPDATE));
    }

    @Test
    public void testUserUpdateNicknameFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setNickname(USER_NICKNAME_TO_UPDATE);
        userForm.setMail(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getNickname().equals(USER_NICKNAME_TO_UPDATE));
    }

    @Test
    public void testUserUpdateNameFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setName(USER_NAME_TO_UPDATE);
        userForm.setNickname(EMPTY_STRING);
        userForm.setMail(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getName().equals(USER_NAME_TO_UPDATE));
    }

    @Test
    public void testUserUpdateSurnameFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setSurname(USER_SURNAME_TO_UPDATE);
        userForm.setNickname(EMPTY_STRING);
        userForm.setMail(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getSurname().equals(USER_SURNAME_TO_UPDATE));
    }

    @Test
    public void testUserUpdateSpecFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setSpec(SPEC_TO_UPDATE);
        userForm.setNickname(EMPTY_STRING);
        userForm.setMail(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getSpec().getId() == SPEC_TO_UPDATE.getId());
    }

    @Test
    public void testUserUpdateMailFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setMail(USER_EMAIL_TO_UPDATE);
        userForm.setNickname(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getMail().equals(USER_EMAIL_TO_UPDATE));
    }

    @Test
    public void testUserUpdateBirthFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setBirth(BIRTH_TO_UPDATE);
        userForm.setNickname(EMPTY_STRING);
        userForm.setMail(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getBirth().compareTo(BIRTH_TO_UPDATE) < 0);
    }

    @Test
    public void testUserUpdateRealmFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setRealm(REALM_TO_UPDATE);
        userForm.setNickname(EMPTY_STRING);
        userForm.setMail(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getRealm().getId() == REALM_TO_UPDATE.getId());
    }

    @Test
    public void testUserUpdatePhoneFromUserForm() {
        User user = userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID);
        User userForm = new User();
        userForm.setPhone(PHONE_TO_UPDATE);
        userForm.setNickname(EMPTY_STRING);
        userForm.setMail(EMPTY_STRING);
        userService.update(user, userForm);
        assertTrue(userDao.find(User.class, DATABASE_UPDATE_TEST_USER_ID).getPhone().equals(PHONE_TO_UPDATE));
    }


}

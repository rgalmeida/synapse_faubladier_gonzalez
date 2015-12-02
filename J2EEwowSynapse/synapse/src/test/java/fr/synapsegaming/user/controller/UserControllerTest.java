package fr.synapsegaming.user.controller;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.synapsegaming.commons.controller.AbstractControllerTest;
import fr.synapsegaming.user.entity.Clazz;
import fr.synapsegaming.user.entity.Race;
import fr.synapsegaming.user.entity.Realm;
import fr.synapsegaming.user.entity.Specialization;
import fr.synapsegaming.user.entity.User;

public class UserControllerTest extends AbstractControllerTest {

    private static final String DATABASE_PASSWORD_VALUE = "neptune13";
    private static final String DATABASE_MAIL_VALUE = "meidi.airouche@gmail.com";
    private static final String HTTP_PASSWORD_PARAMETER = "password";
    private static final String HTTP_MAIL_PARAMETER = "mail";
    private static final String GET_HTTP_METHOD = "GET";
    private static final String POST_HTTP_METHOD = "POST";
    private static final String SUBSCRIBE_VIEW_NAME = "Subscribe";
    private static final String SIGNIN_VIEW_NAME = "Signin";
    private static final String HOME_VIEW_NAME = "Home";
    private static final String USER_SUBSCRIBE_ROUTE = "/user/subscribe";
    private static final String USER_SIGNIN_ROUTE = "/user/signin";
    private static final String USER_LOGOUT_ROUTE = "/user/logout";

    private static final User USER_SUBSCRIBING = new User();
    private static final Realm USER_SUBSCRIBING_REALM = new Realm();
    private static final Race USER_SUBSCRIBING_RACE = new Race();
    private static final Clazz USER_SUBSCRIBING_CLAZZ = new Clazz();
    private static final Specialization USER_SUBSCRIBING_SPEC = new Specialization();
    private static final String USER_SUBSCRIBING_MAIL = "test@test.fr";
    private static final String USER_SUBSCRIBING_PASSWORD = "Test13.";
    private static final String USER_SUBSCRIBING_NICKNAME = "Test";
    private static final int USER_SUBSCRIBING_REALM_ID = 1;
    private static final long USER_SUBSCRIBING_RACE_ID = 1L;
    private static final long USER_SUBSCRIBING_CLAZZ_ID = 1L;
    private static final long USER_SUBSCRIBING_SPEC_ID = 1L;

    @BeforeClass
    public static void setUpClass() {
        USER_SUBSCRIBING_SPEC.setId(USER_SUBSCRIBING_SPEC_ID);
        USER_SUBSCRIBING_CLAZZ.setId(USER_SUBSCRIBING_CLAZZ_ID);
        USER_SUBSCRIBING_RACE.setId(USER_SUBSCRIBING_RACE_ID);
        USER_SUBSCRIBING_REALM.setId(USER_SUBSCRIBING_REALM_ID);
        USER_SUBSCRIBING.setMail(USER_SUBSCRIBING_MAIL);
        USER_SUBSCRIBING.setPassword(USER_SUBSCRIBING_PASSWORD);
        USER_SUBSCRIBING.setNickname(USER_SUBSCRIBING_NICKNAME);
        USER_SUBSCRIBING.setRealm(USER_SUBSCRIBING_REALM);
        USER_SUBSCRIBING.setRace(USER_SUBSCRIBING_RACE);
        USER_SUBSCRIBING.setClazz(USER_SUBSCRIBING_CLAZZ);
        USER_SUBSCRIBING.setSpec(USER_SUBSCRIBING_SPEC);
        USER_SUBSCRIBING.setLegalsAccepted(true);
    }

    @Test
    public void testSigninRoute() throws Exception {
        testRoute(USER_SIGNIN_ROUTE, SIGNIN_VIEW_NAME, GET_HTTP_METHOD);
    }

    @Test
    public void testSubscribeRoute() throws Exception {
        testRoute(USER_SUBSCRIBE_ROUTE, SUBSCRIBE_VIEW_NAME, GET_HTTP_METHOD);
    }

    @Test
    public void testSigninFailureRoute() throws Exception {
        testRoute(USER_SIGNIN_ROUTE, SIGNIN_VIEW_NAME, POST_HTTP_METHOD);
    }

    @Test
    public void testSigninSucceedRoute() throws Exception {
        request.addParameter(HTTP_MAIL_PARAMETER, DATABASE_MAIL_VALUE);
        request.addParameter(HTTP_PASSWORD_PARAMETER, DATABASE_PASSWORD_VALUE);
        testRoute(USER_SIGNIN_ROUTE, HOME_VIEW_NAME, POST_HTTP_METHOD);
    }

    @Test
    public void testLogoutRoute() throws Exception {
        testRoute(USER_LOGOUT_ROUTE, SIGNIN_VIEW_NAME, GET_HTTP_METHOD);
    }

}

package fr.synapsegaming.ui.controller;

import org.junit.Test;

import fr.synapsegaming.commons.controller.AbstractControllerTest;

public class HomeControllerTest extends AbstractControllerTest {
	
	private static final String GET_HTTP_METHOD = "GET";
	private static final String LEGALS_VIEW_NAME = "Legals";
	private static final String ABOUT_VIEW_NAME = "About";
	private static final String HOME_VIEW_NAME = "Home";
	private static final String LEGALS_ROUTE = "/legals";
	private static final String ABOUT_ROUTE = "/about";
	private static final String ROOT_ROUTE = "/";

	@Test
	public void testHomeRoute() throws Exception {
		testRoute(ROOT_ROUTE, HOME_VIEW_NAME, GET_HTTP_METHOD);
	}

	@Test
	public void testAboutRoute() throws Exception {
		testRoute(ABOUT_ROUTE, ABOUT_VIEW_NAME, GET_HTTP_METHOD);
	}

	@Test
	public void testLegalsRoute() throws Exception {
		testRoute(LEGALS_ROUTE, LEGALS_VIEW_NAME, GET_HTTP_METHOD);
	}

}

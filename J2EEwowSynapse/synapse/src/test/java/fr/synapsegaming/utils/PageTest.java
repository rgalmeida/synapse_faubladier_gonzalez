package fr.synapsegaming.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PageTest {

    private static final int PAGE_NUMBER = 1;
    private static final Page ACTIVE_PAGE = new Page(PAGE_NUMBER, "path", true);
    private static final Page INACTIVE_PAGE = new Page(PAGE_NUMBER, "path", false);
    private static final String EMPTY_STRING = "";

    @Test
    public void testPageIsActive() {
        assertTrue(ACTIVE_PAGE.isActive());
    }

    @Test
    public void testPageIsNotActive() {
        assertFalse(INACTIVE_PAGE.isActive());
    }

    @Test
    public void testPageHavePath() {
        assertFalse(ACTIVE_PAGE.getPath().equals(EMPTY_STRING));
    }

    @Test
    public void testPageGotNumber() {
        assertTrue(ACTIVE_PAGE.getNumber() == PAGE_NUMBER);
    }

}

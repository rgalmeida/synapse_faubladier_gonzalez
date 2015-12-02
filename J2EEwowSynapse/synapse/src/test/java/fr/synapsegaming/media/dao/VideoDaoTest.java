package fr.synapsegaming.media.dao;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;

public class VideoDaoTest extends AbstractDaoTest {
	
	private static final String LAST_VIDEO_TITLE = "Video 3";
	private static final int LIST_INITIAL_INDEX = 0;
	private static final int NB_VIDEOS = 2;
	private static final int SINGLE_VIDEO = 1;
	private static final int VIDEO_START_INDEX = 0;
	private static final int NB_VIDEO_TO_LIST = 2;
	
	@Autowired
	VideoDao videoDao;

	@Test
	public void testGetRightNumberOfLastVideos() {
		assertTrue(videoDao.getLastVideos(NB_VIDEOS).size() == NB_VIDEOS);
	}
	
	@Test
	public void testGetLastVideos() {
		assertTrue(videoDao.getLastVideos(SINGLE_VIDEO).get(LIST_INITIAL_INDEX).getTitle().equals(LAST_VIDEO_TITLE));
	}

	@Test
	public void testListVideos() {
		assertTrue(CollectionUtils.isNotEmpty(videoDao.list(VIDEO_START_INDEX, NB_VIDEO_TO_LIST)));
	}

}

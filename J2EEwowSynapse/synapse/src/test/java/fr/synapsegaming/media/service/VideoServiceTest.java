package fr.synapsegaming.media.service;

import static org.junit.Assert.*;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;
import fr.synapsegaming.utils.Paginator;

public class VideoServiceTest extends AbstractServiceTest {

    private static final String VIDEO_PAGE_PATH = "/path/";
    private static final int NB_VIDEOS_PAGINATED = 1;
    private static final int NB_PAGES = 4;
    private static final int NB_VIDEOS_TO_LIST = 3;
    private static final int PAGINATOR_START_NUMBER = 0;
    private static final int PAGINATOR_DATA_NUMBER = 1;
    private static final int PAGINATOR_PAGE_NUMBER = 1;
    private static final Paginator VIDEO_PAGINATOR= new Paginator(PAGINATOR_START_NUMBER, PAGINATOR_DATA_NUMBER, PAGINATOR_PAGE_NUMBER);

    @Autowired
    VideoService videoService;

    @Test
    public void testGetThreeLastVideos() {
        assertTrue(videoService.getThreeLastVideos().size() == NB_VIDEOS_TO_LIST);
    }

    @Test
    public void testList() {
        assertTrue(CollectionUtils.isNotEmpty(videoService.list()));
    }

    @Test
    public void testListPaginator() {
        assertTrue(videoService.list(VIDEO_PAGINATOR).size() == NB_VIDEOS_PAGINATED);
    }

    @Test
    public void testPagesNumber() {
        assertTrue(videoService.pagesNumber(VIDEO_PAGINATOR, VIDEO_PAGE_PATH).size() == NB_PAGES);
    }

}

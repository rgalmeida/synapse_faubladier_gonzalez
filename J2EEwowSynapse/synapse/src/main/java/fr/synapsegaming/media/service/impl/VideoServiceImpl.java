package fr.synapsegaming.media.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.media.dao.VideoDao;
import fr.synapsegaming.media.entity.Video;
import fr.synapsegaming.media.service.VideoService;
import fr.synapsegaming.utils.Page;
import fr.synapsegaming.utils.Paginator;

@Service("VideoService")
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Video> getThreeLastVideos() {
        return videoDao.getLastVideos(3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Video> list() {
        return videoDao.list(Video.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Video> list(Paginator paginator) {
        int start = (paginator.getPageNumber() - 1)
                * paginator.getPageDataNumber();
        return videoDao.list(start, paginator.getPageDataNumber());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Page> pagesNumber(Paginator paginator, String videoPath) {
        Integer videosNumber = videoDao.count(Video.class);
        List<Page> pages = new ArrayList<Page>();
        int pageNumber = 0;
        Page page;
        for (int i = 0; i <= videosNumber; i++) {
            if ((i - 1) % paginator.getPageDataNumber() == 0) {
                pageNumber++;
                if (paginator.getPageNumber() == pageNumber) {
                    page = new Page(pageNumber, videoPath + pageNumber, true);
                } else {
                    page = new Page(pageNumber, videoPath + pageNumber, false);
                }
                pages.add(page);
            }
        }
        return pages;
    }

}

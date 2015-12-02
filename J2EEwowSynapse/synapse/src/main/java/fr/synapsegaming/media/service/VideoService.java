package fr.synapsegaming.media.service;

import java.util.List;

import fr.synapsegaming.media.entity.Video;
import fr.synapsegaming.utils.Page;
import fr.synapsegaming.utils.Paginator;

/**
 * <b>VideoService</b> is the public interface for Video business logic
 * 
 * @author Meidi
 * 
 */
public interface VideoService {

    /**
     * List the three last videos
     * 
     * @return a list of videos
     */
    public List<Video> getThreeLastVideos();

    /**
     * List all the videos
     * 
     * @return a list of Videos
     */
    public List<Video> list();

    /**
     * List all the videos in the limit of the paginator
     * 
     * @param paginator
     *            : the dataset limiter
     * @return a list of Videos
     */
    public List<Video> list(Paginator paginator);

    /**
     * List all the pages to display under the dataset of videos
     * 
     * @param paginator
     *            : the dataset limiter
     * @param videoPath
     *            : the path to video controller
     * @return a list of pages
     */
    public List<Page> pagesNumber(Paginator paginator, String videoPath);
}

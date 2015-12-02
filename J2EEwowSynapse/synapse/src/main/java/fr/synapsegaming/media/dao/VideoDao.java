package fr.synapsegaming.media.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.media.entity.Video;

/**
 * <b>VideoDao</b> is the public interface who describe the Video DAO
 * 
 * @author Meidi
 * 
 */
public interface VideoDao extends Dao<Video, Long> {

    /**
     * Get the x last videos
     * 
     * @param nbVideos
     *            : number of videos to return
     * @return a list of videos
     */
    public List<Video> getLastVideos(int nbVideos);

    /**
     * List the videos between 2 parameters
     * 
     * @param start
     *            : the data number to start with
     * @param nbVideos
     *            : the number of data to display
     * @return a liste of videos
     */
    public List<Video> list(int start, int nbVideos);

}

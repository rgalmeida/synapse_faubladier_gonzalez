package fr.synapsegaming.media.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.media.dao.VideoDao;
import fr.synapsegaming.media.entity.Video;

@Repository("VideoDao")
public class VideoDaoImpl extends AbstractDao<Video, Long> implements VideoDao {

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Video> getLastVideos(int nbVideos) {
        return getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(Video.class).addOrder(
                        Order.desc("id")), 0, nbVideos);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Video> list(int start, int nbVideos) {
        return getHibernateTemplate().findByCriteria(
                DetachedCriteria.forClass(Video.class).addOrder(
                        Order.desc("id")), start, nbVideos);
    }

}

package fr.synapsegaming.social.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.social.dao.ForumDao;
import fr.synapsegaming.social.entity.Forum;

@Repository("ForumDao")
public class ForumDaoImpl extends AbstractDao<Forum, Long> implements ForumDao {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger.getLogger(ForumDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Forum> list(int idGroup) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Forum f join fetch f.groups g where g.id = :idGroup order by f.name");
            query.setParameter("idGroup", idGroup);
            return query.list();
        } catch (Exception e) {
            LOGGER.warn(e);
            LOGGER.warn("Il n'y a pas d'utilisateur enregistré");
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
}

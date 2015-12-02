package fr.synapsegaming.ui.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.ui.dao.ResourceDao;
import fr.synapsegaming.ui.entity.Resource;

@Repository("ResourceDao")
public class ResourceDaoImpl extends AbstractDao<Resource, Long> implements
ResourceDao {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger
            .getLogger(ResourceDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Resource> listResourcesForGroup(int idGroup) {
        Session session = this.getSession();
        
        try {
            Query query = session
                    .createQuery("from Resource r join fetch r.groups g where g.id = :idGroup order by r.order");
            query.setParameter("idGroup", idGroup);
            return query.list();
        } catch (Exception e) {
            LOGGER.warn(e);
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
}

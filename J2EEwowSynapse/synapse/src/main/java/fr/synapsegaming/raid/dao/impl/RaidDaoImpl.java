package fr.synapsegaming.raid.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.RaidDao;
import fr.synapsegaming.raid.entity.Extension;
import fr.synapsegaming.raid.entity.Raid;

@Repository("RaidDao")
public class RaidDaoImpl extends AbstractDao<Raid, Long> implements RaidDao {

    private static final Logger LOGGER = Logger.getLogger(RaidDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Raid> listForExtension(Extension extension) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Raid r join fetch r.patch p join fetch p.extension e where e.id = :idExtension");
            query.setParameter("idExtension", extension.getId());
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
}

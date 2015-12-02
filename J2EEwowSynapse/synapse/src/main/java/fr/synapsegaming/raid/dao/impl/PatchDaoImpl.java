package fr.synapsegaming.raid.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.PatchDao;
import fr.synapsegaming.raid.entity.Patch;

@Repository("PatchDao")
public class PatchDaoImpl extends AbstractDao<Patch, Long> implements PatchDao {

    private static final Logger LOGGER = Logger.getLogger(PatchDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Patch getLastPatch() {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Patch p order by p.versionX DESC, p.versionY DESC, p.versionZ DESC");
            query.setMaxResults(1);
            return (Patch) query.uniqueResult();
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        } finally {
            session.close();
        }
    }
}

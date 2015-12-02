package fr.synapsegaming.raid.dao.impl;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.ExtensionDao;
import fr.synapsegaming.raid.entity.Extension;

@Repository("ExtensionDao")
public class ExtensionDaoImpl extends AbstractDao<Extension, Long> implements
        ExtensionDao {

    private static final Logger LOGGER = Logger
            .getLogger(ExtensionDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Extension getLastExtension() {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Extension s order by s.versionX DESC, s.versionY DESC, s.versionZ DESC");
            query.setMaxResults(1);
            return (Extension) query.uniqueResult();
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        } finally {
            session.close();
        }
    }
}

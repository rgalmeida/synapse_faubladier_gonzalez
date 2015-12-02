package fr.synapsegaming.user.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.user.dao.RealmDao;
import fr.synapsegaming.user.entity.Realm;

@Repository("RealmDao")
public class RealmDaoImpl extends AbstractDao<Realm, Integer> implements
RealmDao {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger.getLogger(RealmDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Realm> listRealmsOrderByName() {
        Session session = this.getSession();
        try {
            Query query = session.createQuery("from Realm r order by r.name");
            return query.list();
        } catch (Exception e) {
            LOGGER.warn(e);
            LOGGER.warn("No Realm found");
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
}

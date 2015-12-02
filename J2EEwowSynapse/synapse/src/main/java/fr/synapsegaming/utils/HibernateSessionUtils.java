package fr.synapsegaming.utils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * <b>HibernateSessionUtils</b> initialize the Hibernate session factory
 * 
 * @author Meidi
 * 
 */
public abstract class HibernateSessionUtils extends HibernateDaoSupport {

    /**
     * Hibernate session factory
     */
    @Autowired
    public void initialize(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}

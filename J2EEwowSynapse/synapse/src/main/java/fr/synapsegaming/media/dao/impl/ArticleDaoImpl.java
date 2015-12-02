package fr.synapsegaming.media.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.media.dao.ArticleDao;
import fr.synapsegaming.media.entity.Article;
import fr.synapsegaming.utils.Paginator;

@Repository("ArticleDao")
public class ArticleDaoImpl extends AbstractDao<Article, Long> implements
        ArticleDao {

    private static final Logger LOGGER = Logger.getLogger(ArticleDaoImpl.class);

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getLastArticlesByType(Long type, Integer nbArticles) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a where a.type.id = :type order by a.id desc");
            query.setParameter("type", type);
            query.setMaxResults(nbArticles);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList<Article>();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getLastArticlesByType(Long type, Paginator paginator) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a where a.type.id = :type order by a.id desc");
            query.setFirstResult((paginator.getPageNumber() - 1)
                    * paginator.getPageDataNumber());
            query.setMaxResults(paginator.getPageStartNumber()
                    + paginator.getPageDataNumber());
            query.setParameter("type", type);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList<Article>();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getLastArticlesByType(Long type, Paginator paginator, int idGroup) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a join fetch a.groups g where a.type.id = :type and g.id = :group order by a.id desc");
            query.setFirstResult((paginator.getPageNumber() - 1)
                    * paginator.getPageDataNumber());
            query.setMaxResults(paginator.getPageStartNumber()
                    + paginator.getPageDataNumber());
            query.setParameter("type", type);
            query.setParameter("group", idGroup);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList<Article>();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer count(long type, int groupId) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a join fetch a.groups g where a.type.id = :type and g.id = :group order by a.id desc");
            query.setParameter("type", type);
            query.setParameter("group", groupId);
            return query.list().size();
        } catch (Exception e) {
            LOGGER.error(e);
            return 0;
        } finally {
            session.close();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer count(long type) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a where a.type.id = :type order by a.id desc");
            query.setParameter("type", type);
            return query.list().size();
        } catch (Exception e) {
            LOGGER.error(e);
            return 0;
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getLastArticlesWithCoverImage(int nbArticles, long type) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a where a.type.id = :type and a.coverImage is not null order by a.id desc");
            query.setParameter("type", type);
            query.setMaxResults(nbArticles);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList<Article>();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Article> getLastArticles(long type, int group, int nbArticles) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a join fetch a.groups g where a.type.id = :type and g.id = :group order by a.id desc");
            query.setParameter("type", type);
            query.setParameter("group", group);
            query.setMaxResults(nbArticles);
            return query.list();
        } catch (Exception e) {
            LOGGER.error(e);
            return new ArrayList<Article>();
        } finally {
            session.close();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Article getArticleByType(long type) {
        Session session = this.getSession();
        try {
            Query query = session
                    .createQuery("from Article a where a.type.id = :type");
            query.setParameter("type", type);
            return (Article) query.uniqueResult();
        } catch (Exception e) {
            LOGGER.error(e);
            return null;
        } finally {
            session.close();
        }
    }

}

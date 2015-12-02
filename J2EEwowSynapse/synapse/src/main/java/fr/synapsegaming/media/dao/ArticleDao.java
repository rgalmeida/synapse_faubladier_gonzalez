package fr.synapsegaming.media.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.media.entity.Article;
import fr.synapsegaming.utils.Paginator;

/**
 * <b>Article</b> is the public interface who describe the resource DAO
 * 
 * @author Meidi
 * 
 */
public interface ArticleDao extends Dao<Article, Long> {

    /**
     * Get the x last articles by type
     * 
     * @param type
     *            : the kind of article to return
     * @param nbArticles
     *            : the number of articles to return
     * @return a list of articles of a kind
     */
    public List<Article> getLastArticlesByType(Long type, Integer nbArticles);

    /**
     * Get all of the last articles by type
     * 
     * @param type
     *            : the kind of article to return
     * @param paginator
     *            : to filter dataset
     * @param idGroup
     *            : the group owner
     * @return a list of articles of a kind
     */
    public List<Article> getLastArticlesByType(Long type, Paginator paginator, int idGroup);

    /**
     * Count the number of articles from a type
     * 
     * @param type
     *            : type of article
     * @param groupId
     *            : group owning
     * @return the number of articles of type "type"
     */
    public Integer count(long type, int groupId);

    /**
     * Count the number of articles from a type
     * 
     * @param type
     *            : type of article
     * @return the number of articles of type "type"
     */
    public Integer count(long type);

    /**
     * List the X last articles with a coverage image
     * 
     * @param nbArticles
     *            : number of articles to return
     * @param type
     *            : the kind of article to return
     * @return a list of Articles
     */
    public List<Article> getLastArticlesWithCoverImage(int nbArticles, long type);

    /**
     * List the X last articles of a kind visible by a group
     * 
     * @param type
     *            : the kind of article
     * @param groupId
     *            : the king of group
     * @param nbArticles
     *            : the max number of articles to display
     * @return ca list of Articles
     */
    public List<Article> getLastArticles(long type, int groupId, int nbArticles);

    /**
     * Get all of the last articles by type
     * 
     * @param type
     *            : the kind of article to return
     * @param paginator
     *            : to filter dataset
     * @return a list of articles of a kind
     */
    public List<Article> getLastArticlesByType(Long type, Paginator paginator);

    /**
     * Get an article by type
     * 
     * @param type
     *            : article type
     * @return an article
     */
    public Article getArticleByType(long type);

}

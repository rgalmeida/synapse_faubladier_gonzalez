package fr.synapsegaming.media.service;

import java.util.List;

import fr.synapsegaming.media.entity.Article;
import fr.synapsegaming.media.enums.ArticleTypeEnum;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.utils.Page;
import fr.synapsegaming.utils.Paginator;

/**
 * <b>ArticleService</b> is the public interface for Article business logic
 * 
 * @author Meidi
 * 
 */
public interface ArticleService {

    /**
     * List the three last articles from any blog
     * 
     * @return a list of blog articles
     */
    public List<Article> getThreeLastBlogArticles();

    /**
     * List the five last articles from any blog
     * 
     * @return a list of blog articles
     */
    public List<Article> getFiveLastBlogArticles();

    /**
     * List all the news
     * 
     * @param paginator
     *            : to filter dataset
     * @param path
     *            : path for 1 page
     * @return a list of articles
     */
    public List<Article> getAllNews(Paginator paginator, String path);

    /**
     * List all the blogs
     * 
     * @param paginator
     *            : to filter dataset
     * @param path
     *            : path for 1 page
     * @param user
     *            : the current user
     * @return a list of articles
     */
    public List<Article> getAllBlogs(Paginator paginator, String path, User user);

    /**
     * List all the pages to display under the dataset of blogs
     * 
     * @param paginator
     *            : the dataset limiter
     * @param path
     *            : the path to blog controller
     * @param type
     *            : the kind of article to count
     * @param user
     *            : the current user
     * @return a list of pages
     */
    public List<Page> pagesNumber(Paginator paginator, String path, ArticleTypeEnum type, User user);

    /**
     * List all the pages to display under the dataset of blogs
     * 
     * @param paginator
     *            : the dataset limiter
     * @param path
     *            : the path to blog controller
     * @param type
     *            : the kind of article to count
     * @return a list of pages
     */
    public List<Page> pagesNumber(Paginator paginator, String path, ArticleTypeEnum type);

    /**
     * Find an article by id
     * 
     * @param id
     *            : the unique identifier of the article
     * @return an Article
     */
    public Article find(long id);

    /**
     * List the five last News with a coverage image
     * 
     * @return
     */
    public List<Article> getFiveLastProms();

    /**
     * List the five last blogs visible by a user
     * 
     * @param user
     *            : the current user
     * @return a list of blogs
     */
    public List<Article> getFiveLastBlogsForUser(User user);

    /**
     * Get the About article
     * 
     * @return about article
     */
    public Article getAboutArticle();

}

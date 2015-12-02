package fr.synapsegaming.media.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.media.dao.ArticleDao;
import fr.synapsegaming.media.entity.Article;
import fr.synapsegaming.media.enums.ArticleTypeEnum;
import fr.synapsegaming.media.service.ArticleService;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.enums.GroupEnum;
import fr.synapsegaming.utils.Page;
import fr.synapsegaming.utils.Paginator;

@Service("ArticleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOGGER = Logger
            .getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getThreeLastBlogArticles() {
        return articleDao.getLastArticlesByType(ArticleTypeEnum.BLOG.getCode(),
                3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getFiveLastBlogArticles() {
        return articleDao.getLastArticlesByType(ArticleTypeEnum.BLOG.getCode(),
                5);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getAllNews(Paginator paginator, String path) {
        return articleDao.getLastArticlesByType(ArticleTypeEnum.NEWS.getCode(),
                paginator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getAllBlogs(Paginator paginator, String path, User user) {
        try {
            return articleDao.getLastArticlesByType(ArticleTypeEnum.BLOG
                    .getCode(), paginator, user.getGroup().getId());
        } catch (Exception e) {
            LOGGER.info(e);
            return articleDao.getLastArticlesByType(
                    ArticleTypeEnum.BLOG.getCode(), paginator,
                    GroupEnum.GUEST.getCode());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Page> pagesNumber(Paginator paginator, String path, ArticleTypeEnum type, User user) {
        Integer blogsNumber = 0;
        try {
            blogsNumber = articleDao.count(type.getCode(), user.getGroup()
                    .getId());
        } catch (Exception e) {
            LOGGER.info(e);
            blogsNumber = articleDao.count(type.getCode(),
                    GroupEnum.GUEST.getCode());
        }
        return listPages(paginator, path, blogsNumber);
    }

    /**
     * List all the pages for a page
     * @param paginator : define the page cutting
     * @param path : path to the page
     * @param blogsNumber : number of blogs articles
     * @return
     */
    private List<Page> listPages(Paginator paginator, String path, Integer blogsNumber) {
        List<Page> pages = new ArrayList<Page>();
        int pageNumber = 0;
        Page page;
        for (int i = 0; i <= blogsNumber; i++) {
            if ((i - 1) % paginator.getPageDataNumber() == 0) {
                pageNumber++;
                if (paginator.getPageNumber() == pageNumber) {
                    page = new Page(pageNumber, path + pageNumber, true);
                } else {
                    page = new Page(pageNumber, path + pageNumber, false);
                }
                pages.add(page);
            }
        }
        return pages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Page> pagesNumber(Paginator paginator, String path, ArticleTypeEnum type) {
        Integer blogsNumber = articleDao.count(type.getCode());
        return listPages(paginator, path, blogsNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Article find(long id) {
        return articleDao.find(Article.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getFiveLastProms() {
        return articleDao.getLastArticlesWithCoverImage(5,
                ArticleTypeEnum.NEWS.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Article> getFiveLastBlogsForUser(User user) {
        try {
            return articleDao.getLastArticles(ArticleTypeEnum.BLOG.getCode(),
                    user.getGroup().getId(), 5);
        } catch (Exception e) {
            LOGGER.info(e);
            return articleDao.getLastArticles(ArticleTypeEnum.BLOG.getCode(),
                    GroupEnum.GUEST.getCode(), 5);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Article getAboutArticle() {
        return articleDao.getArticleByType(ArticleTypeEnum.ABOUT.getCode());
    }

}

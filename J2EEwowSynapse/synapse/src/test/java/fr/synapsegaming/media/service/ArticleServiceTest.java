package fr.synapsegaming.media.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.service.AbstractServiceTest;
import fr.synapsegaming.media.enums.ArticleTypeEnum;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.utils.Paginator;

public class ArticleServiceTest extends AbstractServiceTest {
	
	private static final int NB_BLOGS = 5;
	private static final int FIVE_PROMS = 5;
	private static final int NB_BLOGS_PAGES = 1;
	private static final int NB_NEWS = 5;
	private static final int NB_NEWS_PAGES = 1;
	private static final long USER_ID = 1L;
	private static final long BLOG_ID = 1L;
	private static final String ARTICLE_PATH = "/path/";
	private static final int THREE_BLOGS = 3;
	private static final int FIVE_BLOGS = 5;
	private static final int PAGE_START_NUMBER = 0;
	private static final int PAGE_DATA_NUMBER = 5;
	private static final int PAGE_NUMBER = 1;
	private static final Paginator paginator = new Paginator(PAGE_START_NUMBER, PAGE_DATA_NUMBER, PAGE_NUMBER);
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	UserDao userDao;

	@Test
	public void testGetThreeLastBlogArticles() {
		assertTrue(articleService.getThreeLastBlogArticles().size() == THREE_BLOGS);
	}

	@Test
	public void testGetFiveLastBlogArticles() {
		assertTrue(articleService.getFiveLastBlogArticles().size() == FIVE_BLOGS);
	}

	@Test
	public void testGetAllNews() {
		assertTrue(articleService.getAllNews(paginator, ARTICLE_PATH).size() == NB_NEWS);
	}

	@Test
	public void testGetAllBlogs() {
		assertTrue(articleService.getAllNews(paginator, ARTICLE_PATH).size() == NB_BLOGS);
	}

	@Test
	public void testPagesNumberForUser() {
		assertTrue(articleService.pagesNumber(paginator, ARTICLE_PATH, ArticleTypeEnum.NEWS, userDao.find(User.class, USER_ID)).size() == NB_NEWS_PAGES);
	}

	@Test
	public void testPagesNumber() {
		assertTrue(articleService.pagesNumber(paginator, ARTICLE_PATH, ArticleTypeEnum.BLOG).size() == NB_BLOGS_PAGES);
	}

	@Test
	public void testFind() {
		assertTrue(articleService.find(BLOG_ID) != null);
	}

	@Test
	public void testGetFiveLastProms() {
		assertTrue(articleService.getFiveLastProms().size() == FIVE_PROMS);
	}

	@Test
	public void testGetFiveLastBlogsForUser() {
		assertTrue(articleService.getFiveLastBlogsForUser(userDao.find(User.class, USER_ID)).size() == FIVE_BLOGS);
	}

	@Test
	public void testGetAboutArticle() {
		assertTrue(articleService.getAboutArticle() != null);
	}

}

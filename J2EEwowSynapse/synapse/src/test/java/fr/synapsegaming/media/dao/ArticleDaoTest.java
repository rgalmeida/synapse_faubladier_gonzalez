package fr.synapsegaming.media.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.synapsegaming.commons.dao.AbstractDaoTest;
import fr.synapsegaming.media.enums.ArticleTypeEnum;
import fr.synapsegaming.user.enums.GroupEnum;
import fr.synapsegaming.utils.Paginator;

public class ArticleDaoTest extends AbstractDaoTest {
	
	private static final Integer NB_BLOGS = 5;
	private static final Integer NB_NEWS = 5;
	private static final int PAGE_START_NUMBER = 1;
	private static final int PAGE_DATA_NUMBER = 5;
	private static final int PAGE_NUMBER = 1;
	private static final String ABOUT_TITLE = "About";
	private static final Paginator blogPaginator = new Paginator(PAGE_START_NUMBER, PAGE_DATA_NUMBER, PAGE_NUMBER);
	
	@Autowired
	ArticleDao articleDao;

	@Test
	public void testGetLastArticlesByTypeAndNbArticles() {
		assertTrue(articleDao.getLastArticlesByType(ArticleTypeEnum.BLOG.getCode(), NB_BLOGS).size()==NB_BLOGS);
	}

	@Test
	public void testGetLastArticlesByTypeWithPaginatorForGroup() {
		assertTrue(articleDao.getLastArticlesByType(ArticleTypeEnum.BLOG.getCode(), blogPaginator, GroupEnum.WEBMASTER.getCode()).size()==NB_BLOGS);
	}

	@Test
	public void testCountArticleOfOneKindWithoutGroup() {
		assertTrue(articleDao.count(ArticleTypeEnum.BLOG.getCode(), GroupEnum.WEBMASTER.getCode())==NB_BLOGS);
	}

	@Test
	public void testCountArticleOfOneKindForGroup() {
		assertTrue(articleDao.count(ArticleTypeEnum.BLOG.getCode())==NB_BLOGS);
	}

	@Test
	public void testGetLastArticlesWithCoverImage() {
		assertTrue(articleDao.getLastArticlesWithCoverImage(NB_NEWS, ArticleTypeEnum.NEWS.getCode()).size()==NB_NEWS);
	}

	@Test
	public void testGetLastArticlesByTypeGroupAndNbArticle() {
		assertTrue(articleDao.getLastArticles(ArticleTypeEnum.BLOG.getCode(), GroupEnum.WEBMASTER.getCode(), NB_BLOGS).size()==NB_BLOGS);
	}

	@Test
	public void testGetLastArticlesByTypeAndPaginator() {
		assertTrue(articleDao.getLastArticlesByType(ArticleTypeEnum.BLOG.getCode(), blogPaginator).size()==NB_BLOGS);
	}

	@Test
	public void testGetArticleByType() {
		assertTrue(articleDao.getArticleByType(ArticleTypeEnum.ABOUT.getCode()).getTitle().equals(ABOUT_TITLE));
	}

}

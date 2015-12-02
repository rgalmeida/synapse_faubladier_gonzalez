package fr.synapsegaming.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.synapsegaming.commons.controller.AbstractController;
import fr.synapsegaming.media.enums.ArticleTypeEnum;
import fr.synapsegaming.media.service.ArticleService;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.utils.Paginator;

/**
 * <b>NewsController</b> route every action made from the "News" page
 * 
 * @author Meidi
 * 
 */
@Controller("NewsController")
@SessionAttributes("resources")
@RequestMapping("/news")
public class NewsController extends AbstractController {

    private static final String NEWS_PAGES_HTTP_ATTRIBUTE = "newsPages";

    private static final String NEWS_HTTP_ATTRIBUTE = "news";

    private static final String RESOURCES_HTTP_ATTRIBUTE = "resources";

    private static final String PROMS_HTTP_ATTRIBUTE = "proms";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ResourceService resourceService;

    private static int PAGINATOR_DEFAULT_START_NUMBER = 0;
    private static int PAGINATOR_DEFAULT_DATA_PER_PAGE = 5;
    private static int PAGINATOR_DEFAULT_PAGE = 1;
    private static String NEWS_PAGES_PATH = "/synapse/news/page/";

    /**
     * The default constructor to initialize the page
     * 
     * @return modelAndView
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        page = new ModelAndView("News");
        paginator = new Paginator(PAGINATOR_DEFAULT_START_NUMBER,
                PAGINATOR_DEFAULT_DATA_PER_PAGE, PAGINATOR_DEFAULT_PAGE);
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(NEWS_HTTP_ATTRIBUTE,
                articleService.getAllNews(paginator, NEWS_PAGES_PATH));
        page.addObject(NEWS_PAGES_HTTP_ATTRIBUTE, articleService.pagesNumber(
                paginator, NEWS_PAGES_PATH, ArticleTypeEnum.NEWS));
        return page;
    }

    /**
     * Display the dataset relative to the page id
     * 
     * @param id
     *            : the page id
     * @return a ModelAndView
     */
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public ModelAndView page(@PathVariable int id) {
        page = new ModelAndView("News");
        paginator = new Paginator(PAGINATOR_DEFAULT_START_NUMBER,
                PAGINATOR_DEFAULT_DATA_PER_PAGE, id);
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(NEWS_HTTP_ATTRIBUTE,
                articleService.getAllNews(paginator, NEWS_PAGES_PATH));
        page.addObject(NEWS_PAGES_HTTP_ATTRIBUTE, articleService.pagesNumber(
                paginator, NEWS_PAGES_PATH, ArticleTypeEnum.NEWS));
        return page;
    }

    /**
     * Display the news article entirely
     * 
     * @param id
     *            : the technical unique identifier of the news
     * @return a ModelAndView
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView news(@PathVariable long id) {
        page = new ModelAndView("NewsArticle");
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(NEWS_HTTP_ATTRIBUTE, articleService.find(id));
        return page;
    }

}

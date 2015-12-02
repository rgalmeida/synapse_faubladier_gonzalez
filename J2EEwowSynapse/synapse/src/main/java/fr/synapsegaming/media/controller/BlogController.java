package fr.synapsegaming.media.controller;

import javax.servlet.http.HttpServletRequest;

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
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.utils.Paginator;

/**
 * <b>BlogController</b> route every action made from the "Blog" page
 * 
 * @author Meidi
 * 
 */
@Controller("BlogController")
@SessionAttributes("resources")
@RequestMapping("/blogs")
public class BlogController extends AbstractController {

    private static final String BLOGS_PAGES_HTTP_ATTRIBUTE = "blogsPages";

    private static final String BLOGS_HTTP_ATTRIBUTE = "blogs";

    private static final String RESOURCES_HTTP_ATTRIBUTE = "resources";

    private static final String PROMS_HTTP_ATTRIBUTE = "proms";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ResourceService resourceService;

    private static int PAGINATOR_DEFAULT_START_NUMBER = 0;
    private static int PAGINATOR_DEFAULT_DATA_PER_PAGE = 12;
    private static int PAGINATOR_DEFAULT_PAGE = 1;
    private static String BLOG_PAGES_PATH = "/synapse/blogs/page/";

    /**
     * The default constructor to initialize the page
     * 
     * @param request
     *            : the Http request sent
     * @return modelAndView
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        
    	page = new ModelAndView("Blogs");
        paginator = new Paginator(PAGINATOR_DEFAULT_START_NUMBER,
                PAGINATOR_DEFAULT_DATA_PER_PAGE, PAGINATOR_DEFAULT_PAGE);
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(BLOGS_HTTP_ATTRIBUTE, articleService.getAllBlogs(
                paginator, BLOG_PAGES_PATH, (User) request.getSession()
                        .getAttribute("user")));
        page.addObject(BLOGS_PAGES_HTTP_ATTRIBUTE, articleService.pagesNumber(
                paginator, BLOG_PAGES_PATH, ArticleTypeEnum.BLOG,
                (User) request.getSession().getAttribute("user")));
        return page;
    }

    /**
     * Display the dataset relative to the page id
     * 
     * @param id
     *            : the page id
     * @param request
     *            : Http request sent
     * @return a ModelAndView
     */
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public ModelAndView page(@PathVariable int id, HttpServletRequest request) {
        page = new ModelAndView("Blogs");
        paginator = new Paginator(PAGINATOR_DEFAULT_START_NUMBER,
                PAGINATOR_DEFAULT_DATA_PER_PAGE, id);
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(BLOGS_HTTP_ATTRIBUTE, articleService.getAllBlogs(
                paginator, BLOG_PAGES_PATH, (User) request.getSession()
                        .getAttribute("user")));
        page.addObject(BLOGS_PAGES_HTTP_ATTRIBUTE, articleService.pagesNumber(
                paginator, BLOG_PAGES_PATH, ArticleTypeEnum.BLOG,
                (User) request.getSession().getAttribute("user")));
        return page;
    }

    /**
     * Display the blog article entirely
     * 
     * @param id
     *            : the technical unique identifier of the blog
     * @return a ModelAndView
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView blog(@PathVariable long id) {
        page = new ModelAndView("BlogArticle");
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject("blog", articleService.find(id));
        return page;
    }

}

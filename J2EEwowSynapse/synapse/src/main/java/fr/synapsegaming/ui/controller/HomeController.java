package fr.synapsegaming.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.synapsegaming.commons.controller.AbstractController;
import fr.synapsegaming.media.service.ArticleService;
import fr.synapsegaming.media.service.VideoService;
import fr.synapsegaming.raid.entity.Patch;
import fr.synapsegaming.raid.service.PatchService;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.service.ClazzService;

/**
 * <b>HomeController</b> route every action made from the "Home" page
 * 
 * @author Meidi
 * 
 */
@Controller("HomeController")
@SessionAttributes(value = { "resources", "userResources" })
@RequestMapping("/")
public class HomeController extends AbstractController {

    private static final String PROMS_HTTP_ATTRIBUTE = "proms";

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PatchService patchService;

    @Autowired
    private ClazzService clazzService;


    /**
     * The default constructor to initialize the page
     * 
     * @param request
     *            : the HttpRequest sent
     * @return modelAndView
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        page = new ModelAndView();
        Patch patch = patchService.getLastPatch();
        page.setViewName("Home");
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        if (user != null)
            page.addObject("userResources", resourceService.listUserResources(user.getGroup().getId()));
        page.addObject("videos", videoService.getThreeLastVideos());
        page.addObject("blogsArticles", articleService.getFiveLastBlogsForUser((User) request.getSession().getAttribute("user")));
        page.addObject("patch", patch);
        page.addObject("classes", clazzService.list());
        return page;
    }

    /**
     * Route to the About Page
     * 
     * @return About Page
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about() {
        page = new ModelAndView("About");
        page.addObject("aboutArticle", articleService.getAboutArticle());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        return page;
    }

    /**
     * Route to the Legals Page
     * 
     * @return Legals Page
     */
    @RequestMapping(value = "/legals", method = RequestMethod.GET)
    public ModelAndView legals() {
        page = new ModelAndView("Legals");
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        return page;
    }
    
}

package fr.synapsegaming.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.synapsegaming.commons.controller.AbstractController;
import fr.synapsegaming.media.service.ArticleService;
import fr.synapsegaming.media.service.VideoService;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.utils.Paginator;

/**
 * <b>VideoController</b> route every action made from the "Video" page
 * 
 * @author Meidi
 * 
 */
@Controller("VideoController")
@SessionAttributes("resources")
@RequestMapping("/videos")
public class VideoController extends AbstractController {

    @Autowired
    VideoService videoService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ResourceService resourceService;

    private static int PAGINATOR_DEFAULT_START_NUMBER = 0;
    private static int PAGINATOR_DEFAULT_DATA_PER_PAGE = 12;
    private static int PAGINATOR_DEFAULT_PAGE = 1;
    private static String VIDEO_PAGES_PATH = "/synapse/videos/page/";

    /**
     * {@inheritDoc}
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        page = new ModelAndView("Videos");
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject("proms", articleService.getFiveLastProms());
        paginator = new Paginator(PAGINATOR_DEFAULT_START_NUMBER,
                PAGINATOR_DEFAULT_DATA_PER_PAGE, PAGINATOR_DEFAULT_PAGE);
        page.addObject("videos", videoService.list(paginator));
        page.addObject("videosPages",
                videoService.pagesNumber(paginator, VIDEO_PAGES_PATH));
        return page;
    }

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public ModelAndView page(@PathVariable int id) {
        page = new ModelAndView("Videos");
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject("proms", articleService.getFiveLastProms());
        paginator = new Paginator(PAGINATOR_DEFAULT_START_NUMBER,
                PAGINATOR_DEFAULT_DATA_PER_PAGE, id);
        page.addObject("videos", videoService.list(paginator));
        page.addObject("videosPages",
                videoService.pagesNumber(paginator, VIDEO_PAGES_PATH));
        return page;
    }

}

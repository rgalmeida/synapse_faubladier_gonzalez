package fr.synapsegaming.stats.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.synapsegaming.commons.controller.AbstractController;
import fr.synapsegaming.media.service.ArticleService;
import fr.synapsegaming.media.service.VideoService;
import fr.synapsegaming.raid.entity.Patch;
import fr.synapsegaming.raid.service.PatchService;
import fr.synapsegaming.stats.service.StatsService;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.service.ClazzService;
import fr.synapsegaming.user.service.UserService;

/**
 * <b>HomeController</b> route every action made from the "Home" page
 * 
 * @author Meidi
 * 
 */
@Controller("StatsController")
@SessionAttributes(value = { "resources", "userResources" })
@RequestMapping(value = "/stats", method = {RequestMethod.GET, RequestMethod.POST})
//@RequestMapping("/stats")
public class StatsController extends AbstractController {
	private static final String STATS_PAGE_HTTP_ATTRIBUTE = "statsPages";

	private static final String STATS_HTTP_ATTRIBUTE = "stats";

	private static final String RESOURCES_HTTP_ATTRIBUTE = "resources";


    private static final String PROMS_HTTP_ATTRIBUTE = "proms";
    
    private static final String STATS_SUBMODULE_EXERCISE_INTRODUCTION = "EXERCISE_INTRODUCTION";
    private static final String STATS_SUBMODULE_CHARACTER_PROFILES = "CHARACTER_PROFILES";
    
    private static final String STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_CLASSES = "EXAMINE_CLASSES";
    private static final String STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_RACES = "EXAMINE_RACES";
    private static final String STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_SPECIALIZATIONS = "EXAMINE_SPECIALIZATIONS";
    
    private static final String STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_OUTOFROAD = "OUT_OF_THE_ROAD";

    
    private static final String STATS_SUBMODULE_ABOUT_USERS_WARNINGS = "USERS_WARNINGS";
    private static final String STATS_SUBMODULE_ABOUT_USERS_ACTIVITY = "USERS_SOCIAL_ACTIVITY";
    private static final String STATS_SUBMODULE_WARNING_USER_NOT_VALID = "USER_NOT_VALID";

    
    private static final int	STATS_DEFAULT_NUMBER_OF_ITEMS_TO_SHOW = 5;
    private static final int	STATS_MAX_NUMBER_OF_ITEMS_TO_SHOW = 10;
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

    @Autowired
    private UserService userService;
    
    @Autowired
    private StatsService statsService;

    /**
     * The default constructor to initialize the page
     * 
     * @param request
     *            : the HttpRequest sent
     * @return modelAndView
     */
    @RequestMapping(value="/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home(HttpServletRequest request, 
    		@ModelAttribute("name_control") String nameControl)
    {
        User user = (User) request.getSession().getAttribute("user");
        page = new ModelAndView();
        Patch patch = patchService.getLastPatch();
        page.setViewName("Stats");
        //StatsMenu.jsp links offset adjust
        page.addObject("branchDepth",0);
        //Instead of creating multiple .jsp to manage all the stats, one only page will be needed
  
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        if(user!=null)
            page.addObject("userResources", resourceService.listUserResources(user.getGroup().getId()));

    	page.addObject("submodule",STATS_SUBMODULE_EXERCISE_INTRODUCTION);
       
        
        return page;
    }
    
    
    @RequestMapping(value = "/usersWarnings/", method = RequestMethod.GET)
    public ModelAndView statsAboutUsers(HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");

    	page = new ModelAndView();
        Patch patch = patchService.getLastPatch();
        page.setViewName("Stats");
        page.addObject("branchDepth",1);

        
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        if (user != null)
            page.addObject("userResources", resourceService.listUserResources(user.getGroup().getId()));
        page.addObject("videos", videoService.getThreeLastVideos());
        
        
    	page.addObject("submodule",STATS_SUBMODULE_ABOUT_USERS_WARNINGS);
    	page.addObject("listUsersDefaultAvatar",statsService.listUsersWithDefaultAvatar());
    	return page;
    }
    
    

    
    /*
     * 
     * character profiles
     */
    
    @RequestMapping(value = "/characterProfiles/", method = RequestMethod.GET)
    public ModelAndView characterProfiles(HttpServletRequest request,
    		@ModelAttribute("classesNumItems") String classesNumItems, 
    		@ModelAttribute("racesNumItems") String racesNumItems, 
    		@ModelAttribute("specializationsNumItems") String specializationsNumItems,
    		@ModelAttribute("showComments") String showComments)
    {
        User user = (User) request.getSession().getAttribute("user");

    	page = new ModelAndView();
        Patch patch = patchService.getLastPatch();
        page.setViewName("Stats");
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        
        page.addObject("branchDepth",1);
        if(showComments.equals("")!=true)
        {
        	page.addObject("showComments","1");
        }
        if(user!=null)
        {
	            page.addObject("userResources", resourceService.listUserResources(user.getGroup().getId()));
	    }
        	
    	page.addObject("submodule",STATS_SUBMODULE_CHARACTER_PROFILES);
        Integer mostUsedClassesNumItems;
        try
        {
        	mostUsedClassesNumItems = Integer.parseInt(classesNumItems);
        }
        catch(NumberFormatException e)
        {
        	mostUsedClassesNumItems=STATS_DEFAULT_NUMBER_OF_ITEMS_TO_SHOW;
        }
        
        List<Object> tmpList=null;
        
        tmpList=statsService.listTopClassPlayed(mostUsedClassesNumItems);
        page.addObject("listMostUsedClasses",tmpList);
        page.addObject("numberOfMostUsedClasses",(mostUsedClassesNumItems<=tmpList.size()?mostUsedClassesNumItems:tmpList.size()));
        
        
        Integer mostUsedRacesNumItems;
        try
        {
        	mostUsedRacesNumItems = Integer.parseInt(racesNumItems);
        }
        catch(NumberFormatException e)
        {
        	mostUsedRacesNumItems=STATS_DEFAULT_NUMBER_OF_ITEMS_TO_SHOW;
        }
        tmpList=statsService.getTopRacesPlayed(mostUsedRacesNumItems);
        page.addObject("listMostUsedRaces",tmpList);
        page.addObject("numberOfMostUsedRaces",(mostUsedRacesNumItems<=tmpList.size()?mostUsedRacesNumItems:tmpList.size()));
        
        
        Integer mostUsedSpecializationsNumItems;
        try
        {
        	mostUsedSpecializationsNumItems = Integer.parseInt(specializationsNumItems);
        }
        catch(NumberFormatException e)
        {
        	mostUsedSpecializationsNumItems=STATS_DEFAULT_NUMBER_OF_ITEMS_TO_SHOW;
        }
        tmpList=statsService.listTopSpecializationsPlayed(mostUsedSpecializationsNumItems);
        page.addObject("listMostUsedSpecs",tmpList);
        page.addObject("numberOfMostUsedSpecs",(mostUsedSpecializationsNumItems<=tmpList.size()?mostUsedSpecializationsNumItems:tmpList.size()));
    	return page;
    }
    
    @RequestMapping(value = "/characterProfiles/{topByType}", method = RequestMethod.GET)
    public ModelAndView characterProfilesByType(HttpServletRequest request,
    		@PathVariable String topByType,
    		@ModelAttribute("classesNumItems") String classesNumItems, 
    		@ModelAttribute("racesNumItems") String racesNumItems, 
    		@ModelAttribute("specializationsNumItems") String specializationsNumItems )
    {
        User user = (User) request.getSession().getAttribute("user");

    	page = new ModelAndView();
        Patch patch = patchService.getLastPatch();
        page.setViewName("Stats");
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        
        page.addObject("branchDepth",2);
        
        if(user!=null)
        {
	        
	            page.addObject("userResources", resourceService.listUserResources(user.getGroup().getId()));
	   
	   
        }
        	
    	page.addObject("submodule",STATS_SUBMODULE_CHARACTER_PROFILES);
        Integer mostUsedClassesNumItems;
        try
        {
        	mostUsedClassesNumItems = Integer.parseInt(classesNumItems);
        }
        catch(NumberFormatException e)
        {
        	mostUsedClassesNumItems=STATS_DEFAULT_NUMBER_OF_ITEMS_TO_SHOW;
        }
        
        List<Object> tmpList=null;
        
        tmpList=statsService.listTopClassPlayed(mostUsedClassesNumItems);
        page.addObject("listMostUsedClasses",tmpList);
        page.addObject("numberOfMostUsedClasses",(mostUsedClassesNumItems<=tmpList.size()?mostUsedClassesNumItems:tmpList.size()));
        
        
        Integer mostUsedRacesNumItems;
        try
        {
        	mostUsedRacesNumItems = Integer.parseInt(racesNumItems);
        }
        catch(NumberFormatException e)
        {
        	mostUsedRacesNumItems=STATS_DEFAULT_NUMBER_OF_ITEMS_TO_SHOW;
        }
        tmpList=statsService.getTopRacesPlayed(mostUsedRacesNumItems);
        page.addObject("listMostUsedRaces",tmpList);
        page.addObject("numberOfMostUsedRaces",(mostUsedRacesNumItems<=tmpList.size()?mostUsedRacesNumItems:tmpList.size()));
        
        
        Integer mostUsedSpecializationsNumItems;
        try
        {
        	mostUsedSpecializationsNumItems = Integer.parseInt(specializationsNumItems);
        }
        catch(NumberFormatException e)
        {
        	mostUsedSpecializationsNumItems=STATS_DEFAULT_NUMBER_OF_ITEMS_TO_SHOW;
        }
        tmpList=statsService.listTopSpecializationsPlayed(mostUsedSpecializationsNumItems);
        page.addObject("listMostUsedSpecs",tmpList);
        page.addObject("numberOfMostUsedSpecs",(mostUsedSpecializationsNumItems<=tmpList.size()?mostUsedSpecializationsNumItems:tmpList.size()));
    
        
        
        if(topByType.equals("TopClasses"))
        {
        	page.addObject("examineSpecialization",STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_CLASSES);
        }
        else if(topByType.equals("TopRaces"))
        {
        	page.addObject("examineSpecialization",STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_RACES);
        }
        else if(topByType.equals("TopSpecializations"))
        {
        	page.addObject("examineSpecialization",STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_SPECIALIZATIONS);
        }
        else{
        	page.addObject("examineSpecialization",STATS_SUBMODULE_CHARACTER_PROFILES_EXAMINE_OUTOFROAD);
        }
   
//        page.addObject("videos", videoService.getThreeLastVideos());
        
//        page.addObject("currentDefaultAvatar",User.USER_LINK_DEFAULT_AVATAR);

    //	page.addObject("listUsersDefaultAvatar",statsService.listUsersWithDefaultAvatar());
    	return page;    }

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
    
    
    
    /*
     * SOCIAL STATISTICS
     * 
     *  - Users posts and replys as events => 1pt score
     * 	
     * 
     *  
     */
    @RequestMapping(value = "/userssocialstats/", method = RequestMethod.GET)
    public ModelAndView statsSocial(HttpServletRequest request,

    		@ModelAttribute("numResultsPage")String maxResultsPage, 
    		@ModelAttribute("dateRangeBegin")String dateBegin, 
    		@ModelAttribute("dateRangeEnd") String dateEnd,
    		@ModelAttribute("showComments") String showComments)
    {
        User user = (User) request.getSession().getAttribute("user");
        if(showComments.equals("")!=true)
        {
        	page.addObject("showComments","1");
        }
    	page = new ModelAndView();
        Patch patch = patchService.getLastPatch();
        page.setViewName("Stats");
        page.addObject("branchDepth",1);
        page.addObject("submodule",STATS_SUBMODULE_ABOUT_USERS_ACTIVITY);

        
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        if (user != null)
            page.addObject("userResources", resourceService.listUserResources(user.getGroup().getId()));
        page.addObject("videos", videoService.getThreeLastVideos());
        
        
    	//page.addObject("submodule",STATS_SUBMODULE_ABOUT_USERS);
    	page.addObject("listUsersDefaultAvatar",statsService.listUsersWithDefaultAvatar());
    	
    	
    	
    	//for the users scores
    	page.addObject("currentMaxResultsPage",maxResultsPage);
    	
    	Integer integerNumPage;
    	
    		integerNumPage=0;
    	
    	
    	page.addObject("currentDateRangeBegin",dateBegin);
    	page.addObject("currentDateRangeEnd",dateEnd);
    	page.addObject("testForumPosts",statsService.listUsersActivityScore(dateBegin,dateEnd,(maxResultsPage.equals("")==true?0:Integer.parseInt(maxResultsPage)),integerNumPage));
    	return page;
    }
    
    
    @RequestMapping(value = "/userssocialstats/{numPage}", method = RequestMethod.GET)
    public ModelAndView statsSocial(@PathVariable int numPage, HttpServletRequest request,
    		@ModelAttribute("numResultsPage")String maxResultsPage, 
    		@ModelAttribute("dateRangeBegin")String dateBegin, 
    		@ModelAttribute("dateRangeEnd") String dateEnd)
    {
    	
        User user = (User) request.getSession().getAttribute("user");

    	page = new ModelAndView();
        Patch patch = patchService.getLastPatch();
        page.setViewName("Stats");
        page.addObject("branchDepth",1);
        page.addObject("submodule",STATS_SUBMODULE_ABOUT_USERS_ACTIVITY);

        
        page.addObject("resources", resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        if (user != null)
            page.addObject("userResources", resourceService.listUserResources(user.getGroup().getId()));
        page.addObject("videos", videoService.getThreeLastVideos());
        
        
    	//page.addObject("submodule",STATS_SUBMODULE_ABOUT_USERS);
    	page.addObject("listUsersDefaultAvatar",statsService.listUsersWithDefaultAvatar());
    	
    	
    	
    	//for the users scores
    	page.addObject("currentMaxResultsPage",maxResultsPage);
    	   	
    	
    	page.addObject("currentDateRangeBegin",dateBegin);
    	page.addObject("currentDateRangeEnd",dateEnd);
    	page.addObject("currentPageIndex",numPage);
    	page.addObject("testForumPosts",statsService.listUsersActivityScore(dateBegin,dateEnd,(maxResultsPage.equals("")==true?0:Integer.parseInt(maxResultsPage)),numPage));
    	return page;
    }
    
    
    
}

package fr.synapsegaming.user.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.synapsegaming.commons.controller.AbstractController;
import fr.synapsegaming.media.service.ArticleService;
import fr.synapsegaming.media.service.VideoService;
import fr.synapsegaming.raid.service.PatchService;
import fr.synapsegaming.social.service.MailService;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.user.entity.Clazz;
import fr.synapsegaming.user.entity.Specialization;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.service.ClazzService;
import fr.synapsegaming.user.service.RaceService;
import fr.synapsegaming.user.service.RealmService;
import fr.synapsegaming.user.service.SpecializationService;
import fr.synapsegaming.user.service.UserService;

/**
 * <b>UserController</b> route every action made for a user
 * 
 * @author Meidi
 * 
 */
@Controller("UserController")
@SessionAttributes(value = { "user", "userResources" })
@RequestMapping("/user")
public class UserController extends AbstractController {

    private static final String PROMS_HTTP_ATTRIBUTE = "proms";

    private static final String RESOURCES_HTTP_ATTRIBUTE = "resources";

    private static final String USER_FORM_MODEL_NAME = "userForm";

    private static final String REALMS_HTTP_ATTRIBUTE = "realms";

    private static final String SPECIALIZATIONS_HTTP_ATTRIBUTE = "specializations";

    private static final String CLASSES_HTTP_ATTRIBUTE = "classes";

    private static final String RACES_HTTP_ATTRIBUTE = "races";

    private static final String SUBSCRIBE_VIEW_NAME = "Subscribe";

    private static final String SIGNIN_VIEW_NAME = "Signin";

    @Autowired
    private RaceService raceService;

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PatchService patchService;

    @Autowired
    private RealmService realmService;

    /**
     * The default constructor to initialize the page
     * 
     * @return modelAndView
     */
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signinPage() {
        page = new ModelAndView();
        page.setViewName(SIGNIN_VIEW_NAME);
        return page;
    }

    /**
     * Route to Subscribe page
     * 
     * @return modelAndView
     */
    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public ModelAndView subscribePage() {
        page = new ModelAndView();
        page.setViewName(SUBSCRIBE_VIEW_NAME);
        page.addObject(USER_FORM_MODEL_NAME, new User());
        page.addObject(RACES_HTTP_ATTRIBUTE, raceService.list());
        page.addObject(CLASSES_HTTP_ATTRIBUTE, null);
        page.addObject(SPECIALIZATIONS_HTTP_ATTRIBUTE, null);
        page.addObject(REALMS_HTTP_ATTRIBUTE, realmService.list());
        return page;
    }

    /**
     * Get the Classes for a Race to display in select box
     * 
     * @param idRace
     *            : technical unique identifier of the selected race
     * @return Asynchronous JSON String Response list of classes
     */
    @RequestMapping(value = "/classes/{idRace}", method = RequestMethod.GET)
    @ResponseBody
    public String classesForRace(@PathVariable int idRace) {
        String json = "[";
        for (Clazz c : clazzService.listClassForRace(idRace)) {
            json = json + "{\"id\":\"" + c.getId() + "\", \"name\":\""
                    + c.getName() + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json = json + "]";
        return json;
    }

    /**
     * Get the Specializations for a Class to display in select box
     * 
     * @param idClass
     *            : technical unique identifier for the selected Class
     * @return Asynchronous JSON String Response list of specializations
     */
    @RequestMapping(value = "/specs/{idClass}", method = RequestMethod.GET)
    @ResponseBody
    public String specsForClass(@PathVariable int idClass) {
        String json = "[";
        for (Specialization s : specializationService
                .listSpecsForClass(idClass)) {
            json = json + "{\"id\":\"" + s.getId() + "\", \"name\":\""
                    + s.getName() + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json = json + "]";
        return json;
    }

    /**
     * Subscribe action for a user. If the User's inputs are good, he's recorded
     * as NOT ACTIVE in the database. A mail is sent to activate the User.
     * 
     * @param userForm
     *            : user form's inputs cast in a User object
     * @param bindingResult
     *            bind User's validators with fields
     * @param passwordConfirmation
     *            the password confirmation to compare with the password
     * @param legals
     *            : tells if the user accepted the legals
     * @param request
     *            : the request coming from the view
     * @return a ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ModelAndView subscribe(@ModelAttribute(USER_FORM_MODEL_NAME) @Valid User userForm, BindingResult bindingResult)
            throws UnsupportedEncodingException {
        page = new ModelAndView(SUBSCRIBE_VIEW_NAME, bindingResult.getModel());
        page.addObject(RACES_HTTP_ATTRIBUTE, raceService.list());
        page.addObject(CLASSES_HTTP_ATTRIBUTE, clazzService.listClassForRace(userForm.getRace().getId()));
        page.addObject(SPECIALIZATIONS_HTTP_ATTRIBUTE, specializationService
                .listSpecsForClass(userForm.getClazz().getId()));
        page.addObject(REALMS_HTTP_ATTRIBUTE, realmService.list());
        if (!bindingResult.hasErrors()) {
            User userSubscribed = userService.subscribe(userForm);
            if (userSubscribed != null) {
                mailService.sendSubscriptionMail(userSubscribed);
                info("Vous avez reçu un email avec un lien pour finaliser votre inscription à l'adresse mail que vous avez fournie.");
                page.setViewName(SIGNIN_VIEW_NAME);
            }
        }
        return page;
    }

    /**
     * Activate the user
     * 
     * @param encryptedMail
     *            : the encoded URL param mail (encrypted)
     * @return the connection page
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/activation/{idUser}", method = RequestMethod.GET)
    public ModelAndView activation(@PathVariable long idUser)
            throws UnsupportedEncodingException {
        page = new ModelAndView();
        page.setViewName(SIGNIN_VIEW_NAME);
        User user = userService.find(idUser);
        if (user != null && !user.isActive()) {
            user.setActive(true);
            userService.update(user);
            info("Vous pouvez désormais vous connecter.");
        } else {
            info("Ce compte est déjà activé.");
        }
        return page;
    }

    /**
     * Connect and create the User's session
     * 
     * @param mail
     *            : the unique mail identifying the User
     * @param password
     *            : the password associated to this email
     * @return a ModelAndView
     */
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ModelAndView signin(@ModelAttribute("mail") String mail, @ModelAttribute("password") String password) {
        User user = userService.findByMail(mail);
        String checkResult = userService.userCanSignin(password, user);
        page = new ModelAndView();
        page.setViewName(SIGNIN_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject("videos", videoService.getThreeLastVideos());
        page.addObject("blogsArticles", articleService.getFiveLastBlogsForUser(user));
        if (checkResult == "") {
            page.setViewName("Home");
            page.addObject("user", user);
            this.user = user;
            page.addObject("userResources", resourceService.listUserRestrictedResources(user.getGroup().getId()));
            page.addObject("patch", patchService.getLastPatch());
            page.addObject(CLASSES_HTTP_ATTRIBUTE, clazzService.list());
        } else {
            info(checkResult);
        }
        return page;
    }

    /**
     * Invalidate the session to disconnect the User
     * 
     * @param session
     *            : the user's session
     * @return Home page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        page = new ModelAndView();
        page.setViewName(SIGNIN_VIEW_NAME);
        session.invalidate();
        page.getModelMap().remove("user");
        page.addObject("user", new User());
        this.user = null;
        return page;
    }

    /**
     * Route to the User profile
     * 
     * @return User Page
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView profile(@PathVariable int id) {
        page = new ModelAndView("User");
        
        /*petit patch pour se lier avec usersWarnings*/
        page.addObject(USER_FORM_MODEL_NAME, userService.find(id));
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
       // page.addObject(SPECIALIZATIONS_HTTP_ATTRIBUTE, specializationService.listSpecsForClass(user.getClazz().getId()));
        page.addObject(REALMS_HTTP_ATTRIBUTE, realmService.list());
        return page;
    }

    /**
     * Route to update user's self profile info
     * @param userForm : the user form values
     * @param bindingResult : the bean validation
     * @return page User Profile
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editProfile(@ModelAttribute(USER_FORM_MODEL_NAME) User userForm, BindingResult bindingResult) {
        page = new ModelAndView("User");
        userService.update(user, userForm);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(SPECIALIZATIONS_HTTP_ATTRIBUTE, specializationService.listSpecsForClass(user.getClazz().getId()));
        page.addObject(REALMS_HTTP_ATTRIBUTE, realmService.list());
        return page;
    }

    /**
     * Route to update the user's avatar from Blizzard API
     * @return
     */
    @RequestMapping(value = "/avatar/update", method = RequestMethod.GET)
    public ModelAndView updateAvatar() {
        page = new ModelAndView("User");
        page.addObject(USER_FORM_MODEL_NAME, new User());
        userService.updateUserAvatarFromBlizzard(user, user.getRealm());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(SPECIALIZATIONS_HTTP_ATTRIBUTE, specializationService.listSpecsForClass(user.getClazz().getId()));
        page.addObject(REALMS_HTTP_ATTRIBUTE, realmService.list());
        return page;
    }

}

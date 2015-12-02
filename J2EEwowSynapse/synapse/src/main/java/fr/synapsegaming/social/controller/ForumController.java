package fr.synapsegaming.social.controller;

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
import fr.synapsegaming.social.entity.ForumPost;
import fr.synapsegaming.social.entity.ForumReply;
import fr.synapsegaming.social.service.ForumService;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.user.entity.User;

/**
 * <b>ForumControllerImpl</b> route every action made from the "Forum" page
 * 
 * @author Meidi
 * 
 */
@Controller("ForumController")
@SessionAttributes(value = { "resources", "userResources" })
@RequestMapping("/forum")
public class ForumController extends AbstractController {

    private static final String FORUM_POST_FORM_VIEW_NAME = "ForumPostForm";

    private static final String FORUM_POST_VIEW_NAME = "ForumPost";

    private static final String POST_HTTP_ATTRIBUTE = "post";

    private static final String CATEGORY_HTTP_ATTRIBUTE = "category";

    private static final String FORUMS_HTTP_ATTRIBUTES = "forums";

    private static final String PROMS_HTTP_ATTRIBUTE = "proms";

    private static final String RESOURCES_HTTP_ATTRIBUTE = "resources";

    private static final String FORUM_VIEW_NAME = "Forum";

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ForumService forumService;

    /**
     * The default constructor to initialize the page
     * 
     * @return modelAndView
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page = new ModelAndView(FORUM_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(FORUMS_HTTP_ATTRIBUTES, forumService.list(user));
        return page;
    }

    /**
     * Route to the posts per category page
     * 
     * @param id
     *            : the technical unique identifier for a category
     * @param request
     *            : the HTTP request incoming
     * @return modelAndView
     */
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ModelAndView postsForCategory(@PathVariable int id, HttpServletRequest request) {
        page = new ModelAndView("ForumCategory");
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(CATEGORY_HTTP_ATTRIBUTE, forumService.getCategory(id,
                (User) request.getSession().getAttribute("user")));
        return page;
    }

    /**
     * Route to a post
     * 
     * @param id
     *            : id of the post
     * @param request
     *            : the current HTTP request
     * @return modelAndView
     */
    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView getPostAndReplies(@PathVariable int id, HttpServletRequest request) {
        page = new ModelAndView(FORUM_POST_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(POST_HTTP_ATTRIBUTE, forumService.getPost(id,
                (User) request.getSession().getAttribute("user")));
        return page;
    }

    /**
     * Add a reply to a post route
     * 
     * @param idPost
     *            : technical identifier of the attached post
     * @param request
     *            : the incoming HTTP request
     * @param replyContent
     *            : the content of the reply to add
     * @return
     */
    @RequestMapping(value = "/post/{idPost}/reply/add", method = RequestMethod.POST)
    public ModelAndView addReplyToPost(@PathVariable int idPost, HttpServletRequest request, @ModelAttribute("reply-content") String replyContent) {
        page = new ModelAndView(FORUM_POST_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        forumService.addReply(idPost,
                (User) request.getSession().getAttribute("user"),
                new ForumReply(replyContent));
        page.addObject(POST_HTTP_ATTRIBUTE, forumService.getPost(idPost,
                (User) request.getSession().getAttribute("user")));
        return page;

    }

    /**
     * Route to the new post form
     * 
     * @return a ModelAndView
     */
    @RequestMapping(value = "/category/{idCategory}/post/new", method = RequestMethod.POST)
    public ModelAndView newPost(@PathVariable long idCategory, HttpServletRequest request) {
        page = new ModelAndView(FORUM_POST_FORM_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(CATEGORY_HTTP_ATTRIBUTE, forumService.getCategory(
                idCategory, (User) request.getSession().getAttribute("user")));
        return page;
    }

    /**
     * Route to add a post to a category
     * 
     * @param idCategory
     *            : the unique identifier of the category
     * @param request
     *            : the HttpRequest
     * @param postTitle
     *            : the title of the post
     * @param postContent
     *            : the content of the post
     * @return a ModelAndView
     */
    @RequestMapping(value = "/category/{idCategory}/post/add", method = RequestMethod.POST)
    public ModelAndView addPostToCategory(@PathVariable long idCategory, HttpServletRequest request, @ModelAttribute("newPostTitle") String postTitle, @ModelAttribute("newPostContent") String postContent) {
        page = new ModelAndView(FORUM_POST_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        long idPost = forumService.addPost(idCategory, (User) request
                .getSession().getAttribute("user"), new ForumPost(postTitle,
                        postContent));
        page.addObject(POST_HTTP_ATTRIBUTE, forumService.getPost(idPost,
                (User) request.getSession().getAttribute("user")));
        return page;

    }

    /**
     * Route to delete a reply
     * 
     * @param idCategory
     *            : the category id of the reply's post
     * @param idPost
     *            : the post id of the reply
     * @param idReply
     *            : the reply id
     * @param request
     *            : the HttpServletRequest (session)
     * @return a ModelAndView
     */
    @RequestMapping(value = "/category/{idCategory}/post/{idPost}/reply/{idReply}/delete", method = RequestMethod.POST)
    public ModelAndView deleteReply(@PathVariable long idPost, @PathVariable long idReply, HttpServletRequest request) {
        page = new ModelAndView(FORUM_POST_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        forumService.deleteReply(idReply);
        page.addObject(POST_HTTP_ATTRIBUTE, forumService.getPost(idPost,
                (User) request.getSession().getAttribute("user")));
        return page;
    }

    /**
     * Route to edit a post
     * 
     * @param idCategory
     *            : the category of the post
     * @param idPost
     *            : the post to edit
     * @param request
     *            : the HttpServletRequest
     * @return a ModelAndView
     */
    @RequestMapping(value = "/category/{idCategory}/post/{idPost}/edit", method = RequestMethod.GET)
    public ModelAndView getPostToEdit(@PathVariable long idCategory, @PathVariable long idPost, HttpServletRequest request) {
        page = new ModelAndView(FORUM_POST_FORM_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(POST_HTTP_ATTRIBUTE, forumService.getPost(idPost,
                (User) request.getSession().getAttribute("user")));
        page.addObject(CATEGORY_HTTP_ATTRIBUTE, forumService.getCategory(
                idCategory, (User) request.getSession().getAttribute("user")));
        return page;
    }

    /**
     * Edit an existing post
     * 
     * @param idCategory
     *            : the category of the post
     * @param idPost
     *            : the post to edit
     * @param request
     *            : the HttpServletRequest
     * @param postTitle
     *            : the title of the post
     * @param postContent
     *            : the content of the post
     * @return
     */
    @RequestMapping(value = "/category/{idCategory}/post/{idPost}/edit", method = RequestMethod.POST)
    public ModelAndView editPost(@PathVariable long idPost, HttpServletRequest request, @ModelAttribute("newPostTitle") String postTitle, @ModelAttribute("newPostContent") String postContent) {
        page = new ModelAndView(FORUM_POST_VIEW_NAME);
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        forumService.editPost(idPost,
                (User) request.getSession().getAttribute("user"),
                new ForumPost(postTitle, postContent));
        page.addObject(POST_HTTP_ATTRIBUTE, forumService.getPost(idPost,
                (User) request.getSession().getAttribute("user")));
        return page;
    }

}

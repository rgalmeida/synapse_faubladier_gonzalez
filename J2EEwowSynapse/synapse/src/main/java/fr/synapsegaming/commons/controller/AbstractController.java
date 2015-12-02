package fr.synapsegaming.commons.controller;

import org.springframework.web.servlet.ModelAndView;

import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.utils.Paginator;

/**
 * <b>AbstractController</b> is the common controller for all the views.
 * 
 * @author Meidi
 * 
 */
public abstract class AbstractController {

    /**
     * The response page to render
     */
    protected static ModelAndView page;

    /**
     * The current User
     */
    protected User user;

    /**
     * The paginator for every dataset
     */
    protected Paginator paginator;

    /**
     * Create an "error" HttpRequest attribute containing the error message to
     * display on the view
     * 
     * @param msg
     *            : the message to display as an error
     */
    protected void error(String msg) {
        page.addObject("error", msg);
    }

    /**
     * Create an "info" HttpRequest attribute containing the information message
     * to display on the view
     * 
     * @param msg
     *            : the message to display as an info
     */
    protected void info(String msg) {
        page.addObject("info", msg);
    }

    /**
     * Create a "warn" HttpRequest attribute containing the warning message to
     * display on the view
     * 
     * @param msg
     *            : the message to display as a warning
     */
    protected void warn(String msg) {
        page.addObject("warn", msg);
    }
}

package fr.synapsegaming.raid.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.service.EventService;
import fr.synapsegaming.raid.service.ExtensionService;
import fr.synapsegaming.raid.service.RaidService;
import fr.synapsegaming.social.service.MailService;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.service.UserService;

/**
 * <b>RaidController</b> input for Raid Module HTTP Requests / Responses
 * 
 * @author Meidi
 * 
 */
@Controller("RaidController")
@SessionAttributes("resources")
@RequestMapping("/raids")
public class RaidController extends AbstractController {

    private static final String RAID_EVENT_VIEW_NAME = "RaidEvent";

    private static final String ROSTER_HEALS_HTTP_ATTRIBUTE = "rosterHeals";

    private static final String ROSTER_RANGERS_HTTP_ATTRIBUTE = "rosterRangers";

    private static final String ROSTER_MELEES_HTTP_ATTRIBUTE = "rosterMelees";

    private static final String ROSTER_TANK_HTTP_ATTRIBUTE = "rosterTanks";

    private static final String HEALS_HTTP_ATTRIBUTE = "heals";

    private static final String RANGERS_HTTP_ATTRIBUTE = "rangers";

    private static final String MELEES_HTTP_ATTRIBUTE = "melees";

    private static final String TANKS_HTTP_ATTRIBUTE = "tanks";

    private static final String EVENT_HTTP_ATTRIBUTE = "event";

    private static final String DIFFICULTIES_HTTP_ATTRIBUTE = "difficulties";

    private static final String RAIDS_HTTP_ATTRIBUTE = "raids";

    private static final String EXTENSION_HTTP_ATTRIBUTE = "extension";

    private static final String PROMS_HTTP_ATTRIBUTE = "proms";

    private static final String RESOURCES_HTTP_ATTRIBUTE = "resources";

    private static final Logger LOGGER = Logger.getLogger(RaidController.class);

    @Autowired
    EventService eventService;

    @Autowired
    ArticleService articleService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    RaidService raidService;

    @Autowired
    ExtensionService extensionService;

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    /**
     * Home page for raid calendar
     * 
     * @return ModelAndView
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        page = new ModelAndView("Raid");
        page.addObject("eventForm", new Event());
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(EXTENSION_HTTP_ATTRIBUTE,
                extensionService.getLastExtension());
        page.addObject(RAIDS_HTTP_ATTRIBUTE,
                raidService.getRaidsForCurrentExtension());
        page.addObject(DIFFICULTIES_HTTP_ATTRIBUTE,
                raidService.listDifficulties());
        return page;
    }

    /**
     * List the current month event by default as a JSON String
     * 
     * @return json string
     */
    @RequestMapping(value = "/json/events/{month}/{year}", method = RequestMethod.GET)
    @ResponseBody
    public String getEventsJSON(@PathVariable int month, @PathVariable int year) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String json = "[";
        for (Event event : eventService.getEvents(month, year)) {
            json = json + "{\"id\":\"" + event.getId() + "\"," + "\"name\":\""
                    + event.getName() + "\"," + "\"img\":\""
                    + event.getRaid().getImageUrl() + "\"," + "\"date\":\""
                    + dateFormat.format(event.getDate()) + "\","
                    + "\"timeStart\":\""
                    + timeFormat.format(event.getStartTime()) + "\","
                    + "\"timeStop\":\""
                    + timeFormat.format(event.getStopTime()) + "\","
                    + "\"difficulty\":\"" + event.getDifficulty().getName()
                    + "\"," + "\"state\":\"" + event.getState().getName()
                    + "\"},";
        }
        json = json.substring(0, json.length() - 1);
        json = json + "]";
        return json;
    }

    /**
     * Get a raid acronym for an event and sen it in JSON
     * 
     * @param idRaid
     *            : id of the raid
     * @return a json response
     */
    @RequestMapping(value = "/json/event/acronym/{idRaid}", method = RequestMethod.GET)
    @ResponseBody
    public String getEventAcronymJSON(@PathVariable int idRaid) {
        try {
            return raidService.getRaid(idRaid).getAcronym();
        } catch (Exception e) {
            LOGGER.info(e);
            return "";
        }
    }

    /**
     * Get a raid image for an event and sen it in JSON
     * 
     * @param idRaid
     *            : id of the raid
     * @return a json response
     */
    @RequestMapping(value = "/json/event/image/{idRaid}", method = RequestMethod.GET)
    @ResponseBody
    public String getEventImageJSON(@PathVariable int idRaid) {
        try {
            return raidService.getRaid(idRaid).getImageUrl();
        } catch (Exception e) {
            LOGGER.info(e);
            return "";
        }
    }

    /**
     * Add a new event to the calendar
     * 
     * @return a Model And View
     */
    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public ModelAndView addRaidEventToCalendar(@ModelAttribute("eventForm") @Valid Event eventForm, BindingResult bindingResult) {
        page = new ModelAndView("Raid");
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(EXTENSION_HTTP_ATTRIBUTE,
                extensionService.getLastExtension());
        page.addObject(RAIDS_HTTP_ATTRIBUTE,
                raidService.getRaidsForCurrentExtension());
        page.addObject(DIFFICULTIES_HTTP_ATTRIBUTE,
                raidService.listDifficulties());
        if (!bindingResult.hasErrors()) {
            eventService.create(eventForm);
        }
        return page;
    }

    /**
     * Manage an event from the Calendar
     * 
     * @param idEvent
     *            : the id of the event to manage
     * @return a Model and View
     */
    @RequestMapping(value = "/event/edit/{idEvent}", method = RequestMethod.GET)
    public ModelAndView manageRaidEventToCalendar(@PathVariable long idEvent) {
        page = new ModelAndView(RAID_EVENT_VIEW_NAME);
        return getRosterAttributes(idEvent);
    }

    @RequestMapping(value = "/event/subscribe/{idEvent}", method = RequestMethod.GET)
    public ModelAndView subscribeRaidEvent(@PathVariable long idEvent, HttpServletRequest request) {
        page = new ModelAndView(RAID_EVENT_VIEW_NAME);
        raidService.subscribeEvent(
                (User) request.getSession().getAttribute("user"), idEvent);
        return getRosterAttributes(idEvent);
    }

    @RequestMapping(value = "/event/unsubscribe/{idEvent}", method = RequestMethod.GET)
    public ModelAndView unsubscribeRaidEvent(@PathVariable long idEvent, HttpServletRequest request) {
        page = new ModelAndView(RAID_EVENT_VIEW_NAME);
        raidService.unsubscribeEvent(
                (User) request.getSession().getAttribute("user"), idEvent);
        return getRosterAttributes(idEvent);
    }

    @RequestMapping(value = "/event/{idEvent}/roster/add/{idUser}", method = RequestMethod.GET)
    public ModelAndView addToEventRoster(@PathVariable long idEvent, @PathVariable long idUser) {
        page = new ModelAndView(RAID_EVENT_VIEW_NAME);
        raidService.addToEventRoster(idUser, idEvent);
        return getRosterAttributes(idEvent);
    }

    @RequestMapping(value = "/event/{idEvent}/roster/remove/{idUser}", method = RequestMethod.GET)
    public ModelAndView removeFromEventRoster(@PathVariable long idEvent, @PathVariable long idUser) {
        page = new ModelAndView(RAID_EVENT_VIEW_NAME);
        raidService.removeFromEventRoster(idUser, idEvent);
        return getRosterAttributes(idEvent);
    }

    private ModelAndView getRosterAttributes(long idEvent) {
        page.addObject(RESOURCES_HTTP_ATTRIBUTE, resourceService.listMainMenu());
        page.addObject(PROMS_HTTP_ATTRIBUTE, articleService.getFiveLastProms());
        page.addObject(EVENT_HTTP_ATTRIBUTE, raidService.getEvent(idEvent));
        page.addObject(TANKS_HTTP_ATTRIBUTE,
                raidService.getTanksForEvent(idEvent));
        page.addObject(MELEES_HTTP_ATTRIBUTE,
                raidService.getMeleesForEvent(idEvent));
        page.addObject(RANGERS_HTTP_ATTRIBUTE,
                raidService.getRangersForEvent(idEvent));
        page.addObject(HEALS_HTTP_ATTRIBUTE,
                raidService.getHealsForEvent(idEvent));
        page.addObject(ROSTER_TANK_HTTP_ATTRIBUTE,
                raidService.getRosterTanksForEvent(idEvent));
        page.addObject(ROSTER_MELEES_HTTP_ATTRIBUTE,
                raidService.getRosterMeleesForEvent(idEvent));
        page.addObject(ROSTER_RANGERS_HTTP_ATTRIBUTE,
                raidService.getRosterRangersForEvent(idEvent));
        page.addObject(ROSTER_HEALS_HTTP_ATTRIBUTE,
                raidService.getRosterHealsForEvent(idEvent));
        return page;
    }

}

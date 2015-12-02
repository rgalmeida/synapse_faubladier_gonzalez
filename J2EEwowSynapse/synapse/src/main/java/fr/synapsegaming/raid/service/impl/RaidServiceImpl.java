package fr.synapsegaming.raid.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.raid.dao.DifficultyDao;
import fr.synapsegaming.raid.dao.EventDao;
import fr.synapsegaming.raid.dao.EventRosterDao;
import fr.synapsegaming.raid.dao.EventSubscriberDao;
import fr.synapsegaming.raid.dao.ExtensionDao;
import fr.synapsegaming.raid.dao.RaidDao;
import fr.synapsegaming.raid.entity.Difficulty;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.entity.EventRoster;
import fr.synapsegaming.raid.entity.EventSubscriber;
import fr.synapsegaming.raid.entity.Raid;
import fr.synapsegaming.raid.service.RaidService;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.enums.RoleEnum;

@Service("RaidService")
@Transactional
public class RaidServiceImpl implements RaidService {

    @Autowired
    RaidDao raidDao;

    @Autowired
    ExtensionDao extensionDao;

    @Autowired
    DifficultyDao difficultyDao;

    @Autowired
    EventDao eventDao;

    @Autowired
    EventSubscriberDao eventSubscriberDao;

    @Autowired
    EventRosterDao eventRosterDao;

    @Autowired
    UserDao userDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Raid> getRaidsForCurrentExtension() {
        return raidDao.listForExtension(extensionDao.getLastExtension());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Raid getRaid(long idRaid) {
        return raidDao.find(Raid.class, idRaid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Difficulty> listDifficulties() {
        return difficultyDao.list(Difficulty.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event getEvent(long id) {
        return eventDao.find(Event.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getTanksForEvent(long idEvent) {
        return eventDao.getUsersForRoleAndEvent(idEvent,
                RoleEnum.TANK.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getMeleesForEvent(long idEvent) {
        return eventDao.getUsersForRoleAndEvent(idEvent,
                RoleEnum.MELEE.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getRangersForEvent(long idEvent) {
        return eventDao.getUsersForRoleAndEvent(idEvent,
                RoleEnum.RANGE.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getHealsForEvent(long idEvent) {
        return eventDao.getUsersForRoleAndEvent(idEvent,
                RoleEnum.HEAL.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getRosterTanksForEvent(long idEvent) {
        return eventDao.getRosterUsersForRoleAndEvent(idEvent,
                RoleEnum.TANK.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getRosterMeleesForEvent(long idEvent) {
        return eventDao.getRosterUsersForRoleAndEvent(idEvent,
                RoleEnum.MELEE.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getRosterRangersForEvent(long idEvent) {
        return eventDao.getRosterUsersForRoleAndEvent(idEvent,
                RoleEnum.RANGE.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getRosterHealsForEvent(long idEvent) {
        return eventDao.getRosterUsersForRoleAndEvent(idEvent,
                RoleEnum.HEAL.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void subscribeEvent(User user, long idEvent) {
        Event event = eventDao.find(Event.class, idEvent);
        EventSubscriber eventSubscriber = new EventSubscriber(user, event);
        if (eventSubscriberDao.find(event, user) == null) {
            eventSubscriberDao.save(eventSubscriber);
            event.getEventSubscribers().add(eventSubscriber);
            eventDao.update(event);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unsubscribeEvent(User user, long idEvent) {
        Event event = eventDao.find(Event.class, idEvent);
        List<EventSubscriber> eventSubscriberToRemove = new ArrayList<EventSubscriber>();
        for (EventSubscriber e : event.getEventSubscribers()) {
            if (e.getUser().getId() == user.getId()
                    && e.getEvent().getId() == idEvent) {
                eventSubscriberToRemove.add(e);
                eventSubscriberDao.delete(e);
            }
        }
        event.getEventSubscribers().removeAll(eventSubscriberToRemove);
        eventDao.update(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToEventRoster(long idUser, long idEvent) {
        Event event = eventDao.find(Event.class, idEvent);
        User user = userDao.find(User.class, idUser);
        EventRoster eventRoster = new EventRoster(user, event);
        if (eventRosterDao.find(event, user) == null) {
            eventRosterDao.save(eventRoster);
            event.getRoster().add(eventRoster);
            eventDao.update(event);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeFromEventRoster(long idUser, long idEvent) {
        Event event = eventDao.find(Event.class, idEvent);
        User user = userDao.find(User.class, idUser);
        List<EventRoster> eventRosterToRemove = new ArrayList<EventRoster>();
        for (EventRoster e : event.getRoster()) {
            if (e.getUser().getId() == user.getId()
                    && e.getEvent().getId() == idEvent) {
                eventRosterToRemove.add(e);
                eventRosterDao.delete(e);
            }
        }
        event.getEventSubscribers().removeAll(eventRosterToRemove);
        eventDao.update(event);
    }
}

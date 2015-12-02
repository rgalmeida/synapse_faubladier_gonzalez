package fr.synapsegaming.raid.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.raid.dao.EventDao;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.service.EventService;
import fr.synapsegaming.social.service.MailService;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.enums.GroupEnum;

@Service("EventService")
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    EventDao eventDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MailService mailService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> getCurrentMonthEvents() {
        return this.eventDao.getEvents(new Date());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> getEvents(int month, int year) {
        return this.eventDao.getEvents(month, year);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Event event) {
        this.eventDao.save(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendInvitationMail(Event event) {
        List<Integer> groups = new ArrayList<Integer>();
        groups.add(GroupEnum.ADMIN.getCode());
        groups.add(GroupEnum.WEBMASTER.getCode());
        groups.add(GroupEnum.GUILD.getCode());
        for (User user : userDao.listByGroups(groups)) {
            mailService.sendEventInvitation(user, event);
        }
    }

}

package fr.synapsegaming.raid.dao;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.entity.EventRoster;
import fr.synapsegaming.user.entity.User;

public interface EventRosterDao extends Dao<EventRoster, Long> {

    public EventRoster find(Event event, User user);

}

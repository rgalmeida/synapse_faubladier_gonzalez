package fr.synapsegaming.raid.dao;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.raid.entity.Event;
import fr.synapsegaming.raid.entity.EventSubscriber;
import fr.synapsegaming.user.entity.User;

public interface EventSubscriberDao extends Dao<EventSubscriber, Long> {

    public EventSubscriber find(Event event, User user);

}

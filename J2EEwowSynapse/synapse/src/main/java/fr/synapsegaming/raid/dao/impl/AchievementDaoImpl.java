package fr.synapsegaming.raid.dao.impl;

import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.AchievementDao;
import fr.synapsegaming.raid.entity.Achievement;

@Repository("AchievementDao")
public class AchievementDaoImpl extends AbstractDao<Achievement, Long>
        implements AchievementDao {

}

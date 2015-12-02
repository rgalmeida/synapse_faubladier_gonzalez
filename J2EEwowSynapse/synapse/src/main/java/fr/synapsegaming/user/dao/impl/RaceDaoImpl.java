package fr.synapsegaming.user.dao.impl;

import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.user.dao.RaceDao;
import fr.synapsegaming.user.entity.Race;

@Repository("RaceDao")
public class RaceDaoImpl extends AbstractDao<Race, Long> implements RaceDao {

}

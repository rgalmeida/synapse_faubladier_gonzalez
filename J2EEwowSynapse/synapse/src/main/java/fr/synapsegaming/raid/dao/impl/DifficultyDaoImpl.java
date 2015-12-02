package fr.synapsegaming.raid.dao.impl;

import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.DifficultyDao;
import fr.synapsegaming.raid.entity.Difficulty;

@Repository("DifficultyDao")
public class DifficultyDaoImpl extends AbstractDao<Difficulty, Long> implements
        DifficultyDao {

}

package fr.synapsegaming.raid.dao.impl;

import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.raid.dao.BossDao;
import fr.synapsegaming.raid.entity.Boss;

@Repository("BossDao")
public class BossDaoImpl extends AbstractDao<Boss, Long> implements BossDao {

}

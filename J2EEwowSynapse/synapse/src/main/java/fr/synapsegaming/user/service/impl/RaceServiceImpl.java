package fr.synapsegaming.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.user.dao.RaceDao;
import fr.synapsegaming.user.entity.Race;
import fr.synapsegaming.user.service.RaceService;

@Service("RaceService")
@Transactional
public class RaceServiceImpl implements RaceService {

    @Autowired
    private RaceDao raceDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Race> list() {
        return raceDao.list(Race.class);
    }

}

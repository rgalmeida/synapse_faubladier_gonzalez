package fr.synapsegaming.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.user.dao.ClazzDao;
import fr.synapsegaming.user.entity.Clazz;
import fr.synapsegaming.user.service.ClazzService;

@Service("ClazzService")
@Transactional
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Clazz> list() {
        return clazzDao.list(Clazz.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Clazz> listClassForRace(long idRace) {
        return clazzDao.listClassForRace(idRace);
    }

}

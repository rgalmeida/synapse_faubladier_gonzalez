package fr.synapsegaming.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.user.dao.RealmDao;
import fr.synapsegaming.user.entity.Realm;
import fr.synapsegaming.user.service.RealmService;

@Service("RealmService")
@Transactional
public class RealmServiceImpl implements RealmService {

    @Autowired
    private RealmDao realmDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Realm> list() {
        return realmDao.listRealmsOrderByName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Realm find(int id) {
        return realmDao.find(Realm.class, id);
    }

}

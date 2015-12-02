package fr.synapsegaming.ui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.ui.dao.ResourceDao;
import fr.synapsegaming.ui.entity.Resource;
import fr.synapsegaming.ui.service.ResourceService;
import fr.synapsegaming.user.enums.GroupEnum;

@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Resource> list() {
        return resourceDao.list(Resource.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Resource> listMainMenu() {
        return resourceDao.listResourcesForGroup(GroupEnum.GUEST.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Resource> listUserResources(int idGroup) {
        return resourceDao.listResourcesForGroup(idGroup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Resource> listUserRestrictedResources(int idGroup) {
        List<Resource> resources = new ArrayList<Resource>();
        if (idGroup != GroupEnum.GUEST.getCode())
            resources = resourceDao.listResourcesForGroup(idGroup);
        return resources;
    }

}

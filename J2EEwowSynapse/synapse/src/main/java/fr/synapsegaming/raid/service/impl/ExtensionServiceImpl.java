package fr.synapsegaming.raid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.raid.dao.ExtensionDao;
import fr.synapsegaming.raid.entity.Extension;
import fr.synapsegaming.raid.service.ExtensionService;

@Service("ExtensionService")
@Transactional
public class ExtensionServiceImpl implements ExtensionService {

    @Autowired
    ExtensionDao extensionDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Extension getLastExtension() {
        return extensionDao.getLastExtension();
    }

}

package fr.synapsegaming.raid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.raid.dao.PatchDao;
import fr.synapsegaming.raid.entity.Patch;
import fr.synapsegaming.raid.service.PatchService;

@Service("PatchService")
@Transactional
public class PatchServiceImpl implements PatchService {

    @Autowired
    private PatchDao patchDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Patch getLastPatch() {
        return patchDao.getLastPatch();
    }

}

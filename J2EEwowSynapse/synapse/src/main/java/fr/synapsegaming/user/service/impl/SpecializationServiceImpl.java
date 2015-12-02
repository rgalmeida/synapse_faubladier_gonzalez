package fr.synapsegaming.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.user.dao.SpecializationDao;
import fr.synapsegaming.user.entity.Specialization;
import fr.synapsegaming.user.service.SpecializationService;

@Service("SpecializationService")
@Transactional
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationDao specializationDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Specialization> list() {
        return specializationDao.list(Specialization.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Specialization> listSpecsForClass(long idClass) {
        return specializationDao.listSpecsForClass(idClass);
    }
}

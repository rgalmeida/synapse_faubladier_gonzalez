package fr.synapsegaming.user.service;

import java.util.List;

import fr.synapsegaming.user.entity.Specialization;

/**
 * <b>SpecializationService</b> is the public interface for Specializations
 * business logic
 * 
 * @author Meidi
 * 
 */
public interface SpecializationService {

    /**
     * List every Specializations
     * 
     * @return a list of Specializations
     */
    public List<Specialization> list();

    /**
     * List every Specializations owned by a Clazz
     * 
     * @param idClass
     *            : the clazz owning specs
     * @return a list of Specializations
     */
    public List<Specialization> listSpecsForClass(long idClass);

}

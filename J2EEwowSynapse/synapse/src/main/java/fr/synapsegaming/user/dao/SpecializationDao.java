package fr.synapsegaming.user.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.user.entity.Specialization;

/**
 * <b>SpicializationDao</b> is the public interface who describe the
 * Specialization DAO
 * 
 * @author Meidi
 * 
 */
public interface SpecializationDao extends Dao<Specialization, Long> {

    /**
     * List all the specializations owned by a Class
     * 
     * @param idClass
     *            : the referenced class
     * @return a list of Specializations
     */
    public List<Specialization> listSpecsForClass(long idClass);

}

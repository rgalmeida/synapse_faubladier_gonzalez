package fr.synapsegaming.commons.dao;

import java.io.Serializable;
import java.util.List;

/**
 * <b>Dao</b> is the public interface who describe the general DAO
 * 
 * @author Meidi
 * 
 * @param <T>
 *            : the type of the object to access in the database
 * @param <PK>
 *            : the primary key of the object to access in the database
 */
public interface Dao<T, PK extends Serializable> {

    /**
     * List every objects of the selected type from the data source
     * 
     * @return a list of objects
     */
    public List<T> list(Class<T> c);

    /**
     * Find an object identified by an ID in the database
     * 
     * @param id
     *            : the primary key of the object in database
     * @return a list of objects
     */
    public T find(Class<T> c, PK id);

    /**
     * Save an object in the data source
     * 
     * @param obj
     *            : the object to create in the database
     * @return the generated primary key of the new object in the database
     */
    public PK save(T obj);

    /**
     * Update an existing object in the database
     * 
     */
    public void update(T obj);

    /**
     * Delete an object from the database
     * 
     * @param obj
     *            : the object to delete
     */
    public void delete(T obj);

    /**
     * Count the number of data in the table
     * 
     * @return number of data
     */
    public int count(Class<T> c);

}

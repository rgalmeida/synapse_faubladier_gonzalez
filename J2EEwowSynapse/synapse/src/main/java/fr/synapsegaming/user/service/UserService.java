package fr.synapsegaming.user.service;

import java.util.List;

import fr.synapsegaming.stats.entity.TypeAndQuantity;
import fr.synapsegaming.user.entity.Realm;
import fr.synapsegaming.user.entity.User;

/**
 * <b>UserService</b> is the public interface for User business logic
 * 
 * @author Meidi
 * 
 */
public interface UserService {

    /**
     * Save a user in the database if : The email doesn't already exists
     * 
     * @param user
     * @return
     */
    public User subscribe(User user);

    /**
     * Check if a User exists thanks to his email
     * 
     * @param mail
     *            : the mail to check
     * @return true if the User exists and False if he isn't
     */
    public boolean emailExist(String mail);

    /**
     * Find a User by mail
     * 
     * @param mail
     *            : user's mail
     * @return a User
     */
    public User findByMail(String mail);

    /**
     * Update an existing User
     * 
     * @param user
     *            : the User to update
     */
    public void update(User user);

    /**
     * Find an existing User
     * 
     * @param idUser
     *            : the technical unique id of the User
     * @return a User
     */
    public User find(long idUser);

    /**
     * List all of the users
     * 
     * @return
     */
    public List<User> getAllUsers();

    
    
    
    /**
     * Check if the user can signin. If it doesn't, return the error message. If it does, return empty String.
     * @param password : the user's password to check
     * @param user : the user to check
     * @return a string
     */
    
    
    
    public String userCanSignin(String password, User user);

    /**
     * Update the current user information with those he changed in his profile
     * @param user : the current user in session
     * @param userForm : the user's new informations from the profile form
     */
    public void update(User user, User userForm);

    /**
     * Update the forum avatar of the current user from Blizzard API
     * @param user : current user
     * @param realm : current user realm
     */
    public void updateUserAvatarFromBlizzard(User user, Realm realm);
    
    
    
    
    public List<Object> Test();
    /**
     * Get the list of the top N class played
     * @param n
     */
    
    

	
    

}

package fr.synapsegaming.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.stats.entity.TypeAndQuantity;
import fr.synapsegaming.user.dao.RealmDao;
import fr.synapsegaming.user.dao.SpecializationDao;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.entity.Group;
import fr.synapsegaming.user.entity.Realm;
import fr.synapsegaming.user.entity.Specialization;
import fr.synapsegaming.user.entity.User;
import fr.synapsegaming.user.enums.BlizzardURLEnum;
import fr.synapsegaming.user.enums.GroupEnum;
import fr.synapsegaming.user.service.UserService;
import fr.synapsegaming.utils.CipherUtils;
import fr.synapsegaming.utils.JsonReader;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger
            .getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private SpecializationDao specializationDao;

    @Autowired
    private RealmDao realmDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public User subscribe(User user) {
        user.setActive(false);
        if (!this.emailExist(user.getMail())) {
            user.setPassword(CipherUtils.encrypt(user.getPassword()));
            user.setGroup(new Group(GroupEnum.GUEST.getCode()));
            Realm realm = realmDao.find(Realm.class, user.getRealm().getId());
            user.setForumAvatar(getUserAvatarFromBlizzard(user, realm));
            return userDao.find(User.class, userDao.save(user));
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean emailExist(String mail) {
        if (userDao.findByMail(mail) != null)
            return true;
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByMail(String mail) {
        return userDao.findByMail(mail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User find(long idUser) {
        return userDao.find(User.class, idUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.list(User.class);
    }
    


    /**
     * {@inheritDoc}
     */
    @Override
    public String userCanSignin(String password, User user) {
        if(user == null){
            return "Cet utilisateur n'existe pas";
        } else if(!user.isActive()) {
            return "Cet utilisateur n'a pas encore validé son inscription";
        } else if(!CipherUtils.encrypt(password).equals(user.getPassword())) {
            return "Le mot de passe est erroné.";
        } else {
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user, User userForm) {
        if(userForm.getNickname() != "")
            user.setNickname(userForm.getNickname());
        if(userForm.getName() != "")
            user.setName(userForm.getName());
        if(userForm.getSurname() != "")
            user.setSurname(userForm.getSurname());
        if(userForm.getSpec() != null)
            user.setSpec(specializationDao.find(Specialization.class, userForm.getSpec().getId()));
        if(userForm.getMail() != "")
            user.setMail(userForm.getMail());
        if(userForm.getBirth() != null)
            user.setBirth(userForm.getBirth());
        if(userForm.getRealm() != null)
            user.setRealm(realmDao.find(Realm.class, userForm.getRealm().getId()));
        if(userForm.getPhone() != "")
            user.setPhone(userForm.getPhone());
        userDao.update(user);
    }

    /**
     * Get the current user avatar from Blizzard API
     * @param user : current user
     * @param realm : the user's realm
     * @return the default image or the right one if founds
     */
    private String getUserAvatarFromBlizzard(User user, Realm realm) {
        try {
            JSONObject json = JsonReader.readJsonFromUrl(BlizzardURLEnum.BLIZZARD_EU_WOW_API_CHARACTER.getUrl() + realm.getName() + "/" + user.getNickname() + "?fields=appearance");
            return BlizzardURLEnum.BLIZZARD_EU_THUMBNAIL_URL.getUrl() + json.getString("thumbnail");
        } catch (Exception e) {
            LOGGER.warn("Aucun utilisateur trouvé pour le joueur " + user.getNickname() + "-" + realm.getName());
            LOGGER.warn(e);
            return BlizzardURLEnum.DEFAULT_USER_URL.getUrl();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUserAvatarFromBlizzard(User user, Realm realm) {
        user.setForumAvatar(getUserAvatarFromBlizzard(user, realm));
        userDao.update(user);
    }


	@Override
	public List<Object> Test() {
		// TODO Auto-generated method stub
		return userDao.Test();
	}

	


	
}

package fr.synapsegaming.social.dao.impl;

import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.social.dao.ForumCategoryDao;
import fr.synapsegaming.social.entity.ForumCategory;

@Repository("ForumCategoryDao")
public class ForumCategoryDaoImpl extends AbstractDao<ForumCategory, Long>
implements ForumCategoryDao {

}

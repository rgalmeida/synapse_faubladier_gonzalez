-- HSQLDB DML v1.0.0 for Unit Test Validation
-- Each class must have a commented block that insert data needed for tests

-- Class : ResourceDao -----------------------------------------------------
-- Resources Insertion
INSERT INTO resources (name, path, sort) VALUES('Accueil', '/', 1);
INSERT INTO resources (name, path, sort) VALUES('News', '/news/', 2);
INSERT INTO resources (name, path, sort) VALUES('Videos', '/videos/', 4);
INSERT INTO resources (name, path, sort) VALUES('Blogs', '/blogs/', 5);
-- Groups Insertion
INSERT INTO groups (name) VALUES('Webmaster');
INSERT INTO groups (name) VALUES('Administrator');
INSERT INTO groups (name) VALUES('Guild');
INSERT INTO groups (name) VALUES('Guest');
-- Resource binding to a Group
INSERT INTO groups_resources (id_group, id_resource) VALUES(4, 1);
INSERT INTO groups_resources (id_group, id_resource) VALUES(3, 2);
INSERT INTO groups_resources (id_group, id_resource) VALUES(2, 3);
INSERT INTO groups_resources (id_group, id_resource) VALUES(1, 4);
-----------------------------------------------------------------------------

-- Class : ClazzDao ---------------------------------------------------------
-- Clazz Insertion
INSERT INTO classes (name, img) VALUES('Chaman', '/resources/img/classes/shaman.png');
INSERT INTO classes (name, img) VALUES('Chasseur', '/resources/img/classes/hunt.png');
INSERT INTO classes (name, img) VALUES('Chevalier de la mort', '/resources/img/classes/dk.png');
INSERT INTO classes (name, img) VALUES('Démoniste', '/resources/img/classes/warlock.png');
-- Race Insertion
INSERT INTO races (name) VALUES('Elfe de sang');
INSERT INTO races (name) VALUES('Gobelin');
INSERT INTO races (name) VALUES('Mort vivant');
-- Clazz binding to a Race
INSERT INTO races_classes (id_race, id_class) VALUES(1, 2);
INSERT INTO races_classes (id_race, id_class) VALUES(1, 3);
INSERT INTO races_classes (id_race, id_class) VALUES(1, 4);
INSERT INTO races_classes (id_race, id_class) VALUES(2, 1);
INSERT INTO races_classes (id_race, id_class) VALUES(2, 2);
INSERT INTO races_classes (id_race, id_class) VALUES(2, 3);
INSERT INTO races_classes (id_race, id_class) VALUES(2, 4);
INSERT INTO races_classes (id_race, id_class) VALUES(3, 2);
INSERT INTO races_classes (id_race, id_class) VALUES(3, 3);
INSERT INTO races_classes (id_race, id_class) VALUES(3, 4);
-----------------------------------------------------------------------------

-- Class : RealmDao ---------------------------------------------------------
-- Realm Insertion
INSERT INTO realms (name) VALUES('Krasus');
INSERT INTO realms (name) VALUES('Eitrigg');
INSERT INTO realms (name) VALUES('Sargeras');
INSERT INTO realms (name) VALUES('Ysondre');
-----------------------------------------------------------------------------

-- Class : SpecializationDao ------------------------------------------------
-- Clazz Insertion
INSERT INTO classes (name, img) VALUES('Voleur', '/resources/img/classes/voleur.png');
-- Roles Insertion
INSERT INTO roles (name) VALUES('Tank');
INSERT INTO roles (name) VALUES('Range');
INSERT INTO roles (name) VALUES('Melee');
INSERT INTO roles (name) VALUES('Heal');
-- Specialization Insertion
INSERT INTO specializations (id_class, id_role, name, img, recruiting) VALUES(1, 3, 'Combat', '/resources/img/specs/combat.png', 0);
INSERT INTO specializations (id_class, id_role, name, img, recruiting) VALUES(1, 3, 'Assassinat', '/resources/img/specs/assassinat.png', 0);
INSERT INTO specializations (id_class, id_role, name, img, recruiting) VALUES(1, 3, 'Finesse', '/resources/img/specs/finesse.png', 0);
-----------------------------------------------------------------------------

-- Class : UserDao ----------------------------------------------------------
-- Race Insertion
INSERT INTO races (name) VALUES('Tauren');
-- Class Insertion
INSERT INTO classes (name, img) VALUES('Druide', '/resources/img/classes/druid.png');
-- Role Insertion
INSERT INTO roles (name) VALUES('Melee');
-- Specialization Insertion
INSERT INTO specializations (id_class, id_role, name, img, recruiting) VALUES(1, 1, 'Combat', '/resources/img/specs/combat.png', 0);
-- Group Insertion
INSERT INTO groups (name) VALUES('Administrator');
-- Realm Insertion
INSERT INTO realms (name) VALUES('Krasus');
-- User Insertion
INSERT INTO users (name, surname, birth, mail, password, nickname, id_race, id_class, id_spec, active, legals, id_group, id_realm, avatar_forum) VALUES('Airouche', 'Meidi', '1988-11-13', 'meidi.airouche@gmail.com', 'uNnXXu939f00JTSF/3CG0A==', 'Squash', 1, 1, 1, 1, 1, 1, 1, '/resources/img/default_avatar.png');
INSERT INTO users (name, surname, birth, mail, password, nickname, id_race, id_class, id_spec, active, legals, id_group, id_realm, avatar_forum) VALUES('Airouche', 'Meidi', '1988-11-13', 'mednnet@wanadoo.fr', 'uNnXXu939f00JTSF/3CG0A==', 'XtremZ', 1, 1, 1, 1, 1, 1, 1, '/resources/img/default_avatar.png');
INSERT INTO users (name, surname, birth, mail, password, nickname, id_race, id_class, id_spec, active, legals, id_group, id_realm, avatar_forum) VALUES('Airouche', 'Meidi', '1988-11-13', 'inactive@test.fr', 'uNnXXu939f00JTSF/3CG0A==', 'Inactive', 1, 1, 1, 1, 1, 1, 1, '/resources/img/default_avatar.png');
INSERT INTO users (name, surname, birth, mail, password, nickname, id_race, id_class, id_spec, active, legals, id_group, id_realm, avatar_forum) VALUES('Airouche', 'Meidi', '1988-11-13', 'update@test.fr', 'uNnXXu939f00JTSF/3CG0A==', 'Update', 1, 1, 1, 1, 1, 1, 1, '/resources/img/default_avatar.png');
-----------------------------------------------------------------------------

-- Class : ForumDao ---------------------------------------------------------
-- Group Insertion
INSERT INTO groups (name) VALUES('Webmaster');
INSERT INTO groups (name) VALUES('Administrator');
INSERT INTO groups (name) VALUES('Guild');
INSERT INTO groups (name) VALUES('Guest');
-- Forum Insertion
INSERT INTO f_forum (name, description) VALUES('Candidature', NULL);
INSERT INTO f_forum (name, description) VALUES('Officiers & Admins', NULL);
INSERT INTO f_forum (name, description) VALUES('A lire', NULL);
-- Forum binding to Group
INSERT INTO f_groups_forums (id_group, id_forum) VALUES(2, 1);
INSERT INTO f_groups_forums (id_group, id_forum) VALUES(2, 2);
INSERT INTO f_groups_forums (id_group, id_forum) VALUES(2, 3);
INSERT INTO f_groups_forums (id_group, id_forum) VALUES(4, 1);
-- ForumCategory Insertion
INSERT INTO f_categories (name, description, id_forum) VALUES('Apply', 'Pour nous rejoindre, c''est ici !', 1);
INSERT INTO f_categories (name, description, id_forum) VALUES('Postulation Acceptée', 'Votre candidature est validée', 1);
-- ForumPost Insertion
INSERT INTO f_posts (title, content, date_create, date_update, id_user_update, id_user, id_cat, locked) VALUES('Title1', 'Content1', '2015-01-06 14:16:24', '2015-01-06 14:16:24', 1, 1, 1, 1);
-----------------------------------------------------------------------------

-- Class : RaidDao -----------------------------------------------------------
-- Extension Insertion
INSERT INTO extensions (name, imageUrl, version_x, version_y, version_z) VALUES('Mist of Pandaria', '', 5, 0, 0);
INSERT INTO extensions (name, imageUrl, version_x, version_y, version_z) VALUES('Warlords of Draenor', '/resources/img/wod_logo.png', 6, 0, 0);
-- Patch Insertion
INSERT INTO patches (name, version_x, version_y, version_z, id_extension) VALUES('Siège d''Orgrimmar', 5, 4, 0, 1);
INSERT INTO patches (name, version_x, version_y, version_z, id_extension) VALUES('Blackrock Foundry', 6, 1, 0, 2);
-- Raid Insertion
INSERT INTO raids (name, acronym, imageUrl, id_patch) VALUES('Cognefort', 'HML', '/resources/img/cognefort.png', 1);
INSERT INTO raids (name, acronym, imageUrl, id_patch) VALUES('Fonderie de Rochenoire', 'BRF', '/resources/img/blackrock.png', 2);
-----------------------------------------------------------------------------

-- Class : PatchDao ---------------------------------------------------------
-- Extension Insertion
INSERT INTO extensions (name, imageUrl, version_x, version_y, version_z) VALUES('Mist of Pandaria', '', 5, 0, 0);
INSERT INTO extensions (name, imageUrl, version_x, version_y, version_z) VALUES('Warlords of Draenor', '/resources/img/wod_logo.png', 6, 0, 0);
-- Patch Insertion
INSERT INTO patches (name, version_x, version_y, version_z, id_extension) VALUES('Siège d''Orgrimmar', 5, 4, 0, 1);
INSERT INTO patches (name, version_x, version_y, version_z, id_extension) VALUES('Blackrock Foundry', 6, 1, 0, 2);
-----------------------------------------------------------------------------

-- Class : VideoDao ---------------------------------------------------------
-- Video Insertion
INSERT INTO videos (title, description, integrationCode) VALUES('Sha de l''orgueil 25 HM', 'Synapse vs Sha de l''orgueil 25 HM', '<iframe width="350" height="200" src="//www.youtube.com/embed/WkdxUwTfwFY" frameborder="0" allowfullscreen></iframe>');
INSERT INTO videos (title, description, integrationCode) VALUES('Galakras 25 HM', 'Synapse vs Galakras 25 HM', '<iframe width="350" height="200" src="//www.youtube.com/embed/WEXsxqWNwuc?list=PLMsBsUQb5mfJ65jY0GyFMz8R-2b23DU64" frameborder="0" allowfullscreen></iframe>');
INSERT INTO videos (title, description, integrationCode) VALUES('Video 3', 'Video 3', '<iframe width="350" height="200" src="//www.youtube.com/embed/WEXsxqWNwuc?list=PLMsBsUQb5mfJ65jY0GyFMz8R-2b23DU64" frameborder="0" allowfullscreen></iframe>');
-----------------------------------------------------------------------------

-- Class : ArticleDao -------------------------------------------------------
-- ArticleType Insertion
INSERT INTO articles_types (name) VALUES('Blog');
INSERT INTO articles_types (name) VALUES('News');
INSERT INTO articles_types (name) VALUES('About');
-- Article Insertion
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('blog1', 'Description1', 'Content1', '2014-11-21', '2014-11-21', 1, '/resources/img/blog1.png', NULL, '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('blog2', 'Description', 'Content', '2014-11-21', '2014-11-21', 1, '/resources/img/blog1.png', NULL, '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('blog3', 'Description', 'Content', '2014-11-21', '2014-11-21', 1, '/resources/img/blog1.png', NULL, '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('blog4', 'Description', 'Content', '2014-11-21', '2014-11-21', 1, '/resources/img/blog1.png', NULL, '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('blog5', 'Description', 'Content', '2014-11-21', '2014-11-21', 1, '/resources/img/blog1.png', NULL, '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('About', 'AboutDescription', 'AboutContent', '2014-11-21', '2014-11-21', 3, '/resources/img/blog1.png', NULL, '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('Prom1', 'Description1', 'Content1', '2014-11-22', '2014-11-22', 2, '/resources/img/ascension.png', '/resources/img/banner1.jpg', '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('Prom2', 'Description2', 'Content2', '2014-11-22', '2014-11-22', 2, '/resources/img/ascension.png', '/resources/img/banner1.jpg', '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('Prom3', 'Description3', 'Content3', '2014-11-22', '2014-11-22', 2, '/resources/img/ascension.png', '/resources/img/banner1.jpg', '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('Prom4', 'Description4', 'Content4', '2014-11-22', '2014-11-22', 2, '/resources/img/ascension.png', '/resources/img/banner1.jpg', '/resources/img/header2.jpg', 1);
INSERT INTO articles (title, description, content, date_create, date_update, id_article_type, imageUrl, cover, ImageHeader, id_user) VALUES('Prom5', 'Description5', 'Content5', '2014-11-22', '2014-11-22', 2, '/resources/img/ascension.png', '/resources/img/banner1.jpg', '/resources/img/header2.jpg', 1);
-- Groups Articles Insertion
INSERT INTO groups_articles(id_group, id_article) VALUES(1,1);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,2);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,3);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,4);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,5);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,6);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,7);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,8);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,9);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,10);
INSERT INTO groups_articles(id_group, id_article) VALUES(1,11);
-----------------------------------------------------------------------------

-- liquibase formatted sql
-- changeset regularexpression:init_core_regularexpression.sql
-- preconditions onFail:MARK_RAN onError:WARN
--
-- Dumping data for table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url) VALUES 
('REGULAR_EXPRESSION_MANAGEMENT','regularexpression.adminFeature.regularexpression_management.name',0,'jsp/admin/plugins/regularexpression/ManageRegularExpression.jsp','regularexpression.adminFeature.regularexpression_management.description',0,'regularexpression','SYSTEM','themes/shared/plugins/regularexpression/images/admin/regularexpression.svg',NULL);

-- changeset regularexpression:init_core_regularexpression.sql-rev1
-- preconditions onFail:MARK_RAN onError:WARN
-- The admin user of the core (id 1) gets the feature of the plugin on a fresh install (rights are never granted by an upgrade script)
DELETE FROM core_user_right WHERE id_right = 'REGULAR_EXPRESSION_MANAGEMENT' AND id_user = 1;
INSERT INTO core_user_right (id_right,id_user) VALUES ('REGULAR_EXPRESSION_MANAGEMENT',1);

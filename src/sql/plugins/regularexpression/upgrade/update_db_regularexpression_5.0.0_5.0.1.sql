-- liquibase formatted sql
-- changeset regularexpression:update_db_regularexpression_5.0.0_5.0.1.sql
-- preconditions onFail:MARK_RAN onError:WARN
UPDATE core_admin_right SET icon_url = 'themes/shared/plugins/regularexpression/images/admin/regularexpression.svg' WHERE id_right = 'REGULAR_EXPRESSION_MANAGEMENT';

-- liquibase formatted sql
-- changeset regularexpression:update_db_regularexpression_4.0.2_4.0.3.sql
-- preconditions onFail:MARK_RAN onError:WARN
ALTER TABLE regularexpression_regular_expression MODIFY COLUMN id_expression int AUTO_INCREMENT;
--liquibase formatted sql
--changeset regularexpression:update_db_regularexpression_3.0.0_3.0.1.sql
--preconditions onFail:MARK_RAN onError:WARN
INSERT INTO regularexpression_regular_expression (id_expression, title, regular_expression_value, valid_exemple, information_message, error_message) VALUES ('2', 'Email', '(^([a-zA-Z0-9]+(([\\.\\-\\_]?[a-zA-Z0-9]+)+)?)\\@(([a-zA-Z0-9]+[\\.\\-\\_])+[a-zA-Z]{2,4})$)|(^$)', 'admin@lutece.fr', 'Expression régulière pour les emails.', 'Le format de l''email est incorrect.');
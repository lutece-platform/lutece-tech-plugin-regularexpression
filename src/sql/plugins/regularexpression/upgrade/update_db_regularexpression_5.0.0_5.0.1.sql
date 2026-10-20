-- liquibase formatted sql
-- changeset regularexpression:update_db_regularexpression_5.0.0_5.0.1.sql
-- preconditions onFail:MARK_RAN onError:WARN
UPDATE core_admin_right SET icon_url = 'themes/shared/plugins/regularexpression/images/admin/regularexpression.svg' WHERE id_right = 'REGULAR_EXPRESSION_MANAGEMENT';

-- changeset regularexpression:update_db_regularexpression_5.0.0_5.0.1-rev2.sql
-- preconditions onFail:MARK_RAN onError:WARN
-- comment LUT-34103 : decodes, once, the HTML entities the removed input filter of lutece-core 8 (sanitizeFilterMode, LUT-34109) wrote into the expressions; the messages typed with the rich text editor are read as text by the application
UPDATE regularexpression_regular_expression SET title = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(title, '&#39;', ''''), '&#x27;', ''''), '&#34;', '"'), '&quot;', '"'), '&#96;', '`'), '&#61;', '='), '&#64;', '@'), '&#43;', '+'), '&lt;', '<'), '&gt;', '>'), '&amp;', '&') WHERE title LIKE '%&%;%';
UPDATE regularexpression_regular_expression SET regular_expression_value = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(regular_expression_value, '&#39;', ''''), '&#x27;', ''''), '&#34;', '"'), '&quot;', '"'), '&#96;', '`'), '&#61;', '='), '&#64;', '@'), '&#43;', '+'), '&lt;', '<'), '&gt;', '>'), '&amp;', '&') WHERE regular_expression_value LIKE '%&%;%';
UPDATE regularexpression_regular_expression SET valid_exemple = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(valid_exemple, '&#39;', ''''), '&#x27;', ''''), '&#34;', '"'), '&quot;', '"'), '&#96;', '`'), '&#61;', '='), '&#64;', '@'), '&#43;', '+'), '&lt;', '<'), '&gt;', '>'), '&amp;', '&') WHERE valid_exemple LIKE '%&%;%';
UPDATE regularexpression_regular_expression SET information_message = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(information_message, '&#39;', ''''), '&#x27;', ''''), '&#34;', '"'), '&quot;', '"'), '&#96;', '`'), '&#61;', '='), '&#64;', '@'), '&#43;', '+'), '&lt;', '<'), '&gt;', '>'), '&amp;', '&') WHERE information_message LIKE '%&%;%';
UPDATE regularexpression_regular_expression SET error_message = REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(error_message, '&#39;', ''''), '&#x27;', ''''), '&#34;', '"'), '&quot;', '"'), '&#96;', '`'), '&#61;', '='), '&#64;', '@'), '&#43;', '+'), '&lt;', '<'), '&gt;', '>'), '&amp;', '&') WHERE error_message LIKE '%&%;%';

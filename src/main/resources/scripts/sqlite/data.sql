-- POPULATING DATA

INSERT INTO privilege (id,name) VALUES 
(17,'READ_PRIVILEGE')
,(18,'WRITE_PRIVILEGE')
;

INSERT INTO role (id,name) VALUES 
(19,'ROLE_ADMIN')
;

INSERT INTO roles_privileges (role_id,privilege_id) VALUES 
(19,17)
,(19,18)
;

INSERT INTO administrators (creation_date,email,full_name,last_modification,password,enabled) VALUES 
(NULL,'admin@localhost','Administrator',NULL,'$2a$10$1Ah1EWAnx1LBKYfzPQnMK.1gx8555GySZTWpnwo9gsDjwq5XkdqIW',true)
;

INSERT INTO users_roles (user_id,role_id) VALUES 
(1,19)
;

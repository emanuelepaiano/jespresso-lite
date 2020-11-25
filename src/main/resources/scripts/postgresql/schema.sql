-- Drop table

-- DROP TABLE public.access_logs;

CREATE TABLE if not exists public.access_logs (
	id bigserial NOT NULL,
	accesspoint_mac varchar(255) NOT NULL,
	browser varchar(255) NULL,
	device_ip varchar(255) NOT NULL,
	device_mac varchar(255) NOT NULL,
	expire_login_on timestamp NOT NULL,
	lastlogin timestamp NOT NULL,
	operating_system varchar(255) NULL,
	remove_session_on timestamp NOT NULL,
	CONSTRAINT access_logs_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.administrators;

CREATE TABLE if not exists  public.administrators (
	id bigserial NOT NULL,
	creation_date timestamp NULL,
	email varchar(255) NOT NULL,
	full_name varchar(255) NULL,
	last_modification timestamp NULL,
	"password" varchar(255) NOT NULL,
	enabled bool NULL,
	CONSTRAINT administrators_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.privilege;

CREATE TABLE if not exists public.privilege (
	id BIGSERIAL NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT privilege_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public."role";

CREATE TABLE if not exists public."role" (
	id BIGSERIAL NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.roles_privileges;

CREATE TABLE if not exists public.roles_privileges (
	role_id BIGSERIAL NOT NULL,
	privilege_id int8 NOT NULL,
	CONSTRAINT fk5yjwxw2gvfyu76j3rgqwo685u FOREIGN KEY (privilege_id) REFERENCES privilege(id),
	CONSTRAINT fk9h2vewsqh8luhfq71xokh4who FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Drop table

-- DROP TABLE public.sessions;

CREATE TABLE if not exists public.sessions (
	id BIGSERIAL NOT NULL,
	accesspoint_mac varchar(255) NOT NULL,
	browser varchar(255) NULL,
	device_ip varchar(255) NOT NULL,
	device_mac varchar(255) NOT NULL,
	expire_login_on timestamp NOT NULL,
	lastlogin timestamp NOT NULL,
	operating_system varchar(255) NULL,
	remove_session_on timestamp NOT NULL,
	CONSTRAINT sessions_id_check CHECK ((id >= 1)),
	CONSTRAINT sessions_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.settings;

CREATE TABLE if not exists public.settings (
	id BIGSERIAL NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	"type" varchar(255) NOT NULL,
	value varchar(255) NOT NULL,
	CONSTRAINT settings_id_check CHECK ((id >= 1)),
	CONSTRAINT settings_pkey PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.users_roles;

CREATE TABLE if not exists public.users_roles (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT fk2a2uoy4kffunoiu0t38l00kps FOREIGN KEY (user_id) REFERENCES administrators(id),
	CONSTRAINT fkt4v0rrweyk393bdgt107vdx0x FOREIGN KEY (role_id) REFERENCES role(id)
);


-- POPULATING DATA

-- INSERT INTO public.privilege (id,"name") VALUES 
-- (17,'READ_PRIVILEGE')
-- ,(18,'WRITE_PRIVILEGE')
-- ON CONFLICT DO NOTHING;

-- INSERT INTO public."role" (id,"name") VALUES 
-- (19,'ROLE_ADMIN')
-- ON CONFLICT DO NOTHING;

-- INSERT INTO public.roles_privileges (role_id,privilege_id) VALUES 
-- (19,17)
-- ,(19,18)
--  ON CONFLICT DO NOTHING;

-- adding user with username "admin@localhost" and password "password"

-- INSERT INTO public.administrators (creation_date,email,full_name,last_modification,"password",enabled) VALUES 
-- (NULL,'admin@localhost','Administrator',NULL,'$2a$10$1Ah1EWAnx1LBKYfzPQnMK.1gx8555GySZTWpnwo9gsDjwq5XkdqIW',true)
-- ON CONFLICT DO NOTHING;

-- INSERT INTO public.users_roles (user_id,role_id) VALUES 
-- (1,19)
-- ON CONFLICT DO NOTHING;


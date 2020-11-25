-- drop table if exists access_logs;
-- drop table if exists administrators;
-- drop table if exists hibernate_sequence;
-- drop table if exists privilege;
-- drop table if exists role;
-- drop table if exists roles_privileges;
-- drop table if exists sessions;
-- drop table if exists settings;
-- drop table if exists users_roles;

create table if not exists access_logs (
       id bigint not null,
        accesspoint_mac varchar(255) not null,
        browser varchar(255),
        device_ip varchar(255) not null,
        device_mac varchar(255) not null,
        expire_login_on timestamp not null,
        lastlogin timestamp not null,
        operating_system varchar(255),
        remove_session_on timestamp not null,
        primary key (id)
    );

create table if not exists administrators (
       id bigint integer,
        creation_date timestamp,
        email varchar(255) not null,
        full_name varchar(255),
        last_modification timestamp,
        password varchar(255) not null,
        enabled boolean,
        primary key (id)
    );

create table if not exists privilege (
       id bigint integer,
        name varchar(255),
        primary key (id)
    );

create table if not exists role (
       id bigint integer,
        name varchar(255),
        primary key (id)
    );

create table if not exists roles_privileges (
       role_id bigint not null,
        privilege_id bigint not null
    );

create table if not exists sessions (
       id bigint not null check (id>=1),
        accesspoint_mac varchar(255) not null,
        browser varchar(255),
        device_ip varchar(255) not null,
        device_mac varchar(255) not null,
        expire_login_on timestamp not null,
        lastlogin timestamp not null,
        operating_system varchar(255),
        remove_session_on timestamp not null,
        primary key (id)
    );

create table if not exists settings (
       id bigint not null check (id>=1),
        description varchar(255) not null,
        name varchar(255) not null,
        type varchar(255) not null,
        value varchar(255) not null,
        primary key (id)
    );

create table if not exists users_roles (
       user_id bigint not null,
        role_id bigint not null
    );

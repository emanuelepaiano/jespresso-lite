-- drop table access_logs if exists;
-- drop table administrators if exists;
-- drop table privilege if exists;
-- drop table role if exists;
-- drop table roles_privileges if exists;
-- drop table sessions if exists;
-- drop table settings if exists;
-- drop table users_roles if exists;
-- drop sequence if exists hibernate_sequence;

create sequence hibernate_sequence start with 1 increment by 1;

create table  access_logs (
       id bigint auto_increment,
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

create table  administrators (
       id bigint auto_increment,
        creation_date timestamp,
        email varchar(255) not null,
        full_name varchar(255),
        last_modification timestamp,
        password varchar(255) not null,
        enabled boolean,
        primary key (id)
    );

create table  privilege (
       id bigint auto_increment,
        name varchar(255),
        primary key (id)
    );

create table role (
       id bigint auto_increment,
        name varchar(255),
        primary key (id)
    );

create table  roles_privileges (
       role_id bigint not null,
        privilege_id bigint not null
    );

create table  sessions (
       id bigint auto_increment,
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

create table  settings (
       id bigint auto_increment,
        description varchar(255) not null,
        name varchar(255) not null,
        type varchar(255) not null,
        value varchar(255) not null,
        primary key (id)
    );

create table users_roles (
       user_id bigint not null,
        role_id bigint not null
    );

alter table roles_privileges 
       add constraint FK5yjwxw2gvfyu76j3rgqwo685u 
       foreign key (privilege_id) 
       references privilege;

alter table roles_privileges 
       add constraint FK9h2vewsqh8luhfq71xokh4who 
       foreign key (role_id) 
       references role;

alter table users_roles 
       add constraint FKt4v0rrweyk393bdgt107vdx0x 
       foreign key (role_id) 
       references role;

alter table users_roles 
       add constraint FK2a2uoy4kffunoiu0t38l00kps 
       foreign key (user_id) 
       references administrators;


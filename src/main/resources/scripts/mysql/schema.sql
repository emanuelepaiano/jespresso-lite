-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Apr 05, 2020 alle 16:33
-- Versione del server: 10.1.44-MariaDB-0ubuntu0.18.04.1
-- Versione PHP: 7.2.24-0ubuntu0.18.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jespresso`
--

-- --------------------------------------------------------

create table if not exists access_logs (
  id bigint not null auto_increment,
  accesspoint_mac varchar(255) not null,
  browser varchar(255),
  device_ip varchar(255) not null,
  device_mac varchar(255) not null,
  expire_login_on datetime not null,
  lastlogin datetime not null,
  operating_system varchar(255),
  remove_session_on datetime not null,
  primary key (id)
) engine = InnoDB;
create table if not exists administrators (
  id bigint not null auto_increment,
  creation_date datetime,
  email varchar(255) not null,
  full_name varchar(255),
  last_modification datetime,
  password varchar(255) not null,
  enabled bit,
  primary key (id)
) engine = InnoDB;
create table if not exists privilege (
  id bigint not null auto_increment,
  name varchar(255),
  primary key (id)
) engine = InnoDB;
create table if not exists role (
  id bigint not null auto_increment,
  name varchar(255),
  primary key (id)
) engine = InnoDB;
create table if not exists roles_privileges (
  role_id bigint not null,
  privilege_id bigint not null
) engine = InnoDB;
create table if not exists sessions (
  id bigint not null auto_increment,
  accesspoint_mac varchar(255) not null,
  browser varchar(255),
  device_ip varchar(255) not null,
  device_mac varchar(255) not null,
  expire_login_on datetime(6) not null,
  lastlogin datetime(6) not null,
  operating_system varchar(255),
  remove_session_on datetime(6) not null,
  primary key (id)
) engine = InnoDB;
create table if not exists settings (
  id bigint not null auto_increment,
  description varchar(255) not null,
  name varchar(255) not null,
  type varchar(255) not null,
  value varchar(255) not null,
  primary key (id)
) engine = InnoDB;
create table if not exists users_roles (
  user_id bigint not null,
  role_id bigint not null
) engine = InnoDB;
alter table
  roles_privileges
add
  foreign key (privilege_id) references privilege (id);
alter table
  roles_privileges
add
  foreign key (role_id) references role (id);
alter table
  users_roles
add
  foreign key (role_id) references role (id);
alter table
  users_roles
add
  foreign key (user_id) references administrators (id);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

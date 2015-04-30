drop schema if exists `computer-database-db`;
create schema if not exists `computer-database-db`;
use `computer-database-db`;

drop table if exists computer;
drop table if exists company;
drop table if exists users;
drop table if exists user_roles;
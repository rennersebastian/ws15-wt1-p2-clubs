# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table event (
  id                        varchar(255) not null,
  eventname                 varchar(255),
  date                      varchar(255),
  time                      varchar(255),
  constraint pk_event primary key (id))
;

create table team (
  id                        bigint not null,
  name                      varchar(255),
  founded                   timestamp,
  constraint pk_team primary key (id))
;

create table user (
  id                        bigint not null,
  user_name                 varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  constraint pk_user primary key (id))
;

create sequence event_seq;

create sequence team_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists event;

drop table if exists team;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists event_seq;

drop sequence if exists team_seq;

drop sequence if exists user_seq;


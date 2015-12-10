# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table club (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_club primary key (id))
;

create table event (
  id                        varchar(255) not null,
  eventname                 varchar(255),
  date                      varchar(255),
  constraint pk_event primary key (id))
;

create table user (
  id                        varchar(255) not null,
  username                  varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  constraint pk_user primary key (id))
;

create sequence club_seq;

create sequence event_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists club;

drop table if exists event;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists club_seq;

drop sequence if exists event_seq;

drop sequence if exists user_seq;


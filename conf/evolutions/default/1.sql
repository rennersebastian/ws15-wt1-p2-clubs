# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table or_event (
  event_id                  bigint not null,
  event_name                varchar(255),
  date                      timestamp,
  team_team_id              bigint,
  constraint pk_or_event primary key (event_id))
;

create table or_invite (
  invite_id                 bigint not null,
  accept                    integer,
  invited                   timestamp,
  myevent_event_id          bigint,
  member_user_id            bigint,
  constraint pk_or_invite primary key (invite_id))
;

create table or_team (
  team_id                   bigint not null,
  name                      varchar(255),
  founded                   timestamp,
  constraint pk_or_team primary key (team_id))
;

create table or_user (
  user_id                   bigint not null,
  user_name                 varchar(255),
  sha_password              varbinary(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  constraint pk_or_user primary key (user_id))
;


create table or_team_user (
  or_team_team_id                bigint not null,
  or_user_user_id                bigint not null,
  constraint pk_or_team_user primary key (or_team_team_id, or_user_user_id))
;

create table or_user_team (
  or_user_user_id                bigint not null,
  or_team_team_id                bigint not null,
  constraint pk_or_user_team primary key (or_user_user_id, or_team_team_id))
;
create sequence or_event_seq;

create sequence or_invite_seq;

create sequence or_team_seq;

create sequence or_user_seq;

alter table or_event add constraint fk_or_event_team_1 foreign key (team_team_id) references or_team (team_id) on delete restrict on update restrict;
create index ix_or_event_team_1 on or_event (team_team_id);
alter table or_invite add constraint fk_or_invite_myevent_2 foreign key (myevent_event_id) references or_event (event_id) on delete restrict on update restrict;
create index ix_or_invite_myevent_2 on or_invite (myevent_event_id);
alter table or_invite add constraint fk_or_invite_member_3 foreign key (member_user_id) references or_user (user_id) on delete restrict on update restrict;
create index ix_or_invite_member_3 on or_invite (member_user_id);



alter table or_team_user add constraint fk_or_team_user_or_team_01 foreign key (or_team_team_id) references or_team (team_id) on delete restrict on update restrict;

alter table or_team_user add constraint fk_or_team_user_or_user_02 foreign key (or_user_user_id) references or_user (user_id) on delete restrict on update restrict;

alter table or_user_team add constraint fk_or_user_team_or_user_01 foreign key (or_user_user_id) references or_user (user_id) on delete restrict on update restrict;

alter table or_user_team add constraint fk_or_user_team_or_team_02 foreign key (or_team_team_id) references or_team (team_id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists or_event;

drop table if exists or_invite;

drop table if exists or_team;

drop table if exists or_team_user;

drop table if exists or_user;

drop table if exists or_user_team;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists or_event_seq;

drop sequence if exists or_invite_seq;

drop sequence if exists or_team_seq;

drop sequence if exists or_user_seq;


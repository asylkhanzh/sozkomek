# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint not null,
  name                      varchar(255),
  password                  varchar(255),
  status_id                 bigint,
  usertype_id               integer,
  groupuser_id              integer,
  current_amount            float,
  blocked_amount            float,
  constraint pk_account primary key (id))
;

create table address (
  id                        bigint not null,
  objecttype_id             integer,
  object_id                 bigint,
  district_id               integer,
  street                    varchar(255),
  building                  varchar(255),
  apartment                 varchar(255),
  status_id                 bigint,
  constraint pk_address primary key (id))
;

create table bound (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_bound primary key (id))
;

create table city (
  id                        integer not null,
  name                      varchar(255),
  status_id                 bigint,
  constraint pk_city primary key (id))
;

create table datelist (
  id                        bigint not null,
  objecttype_id             integer,
  object_id                 bigint,
  datetype_id               integer,
  value                     timestamp,
  status_id                 bigint,
  constraint pk_datelist primary key (id))
;

create table datetype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_datetype primary key (id))
;

create table district (
  id                        integer not null,
  city_id                   integer,
  name                      varchar(255),
  status_id                 bigint,
  constraint pk_district primary key (id))
;

create table emailbook (
  id                        bigint not null,
  account_id                bigint,
  email                     varchar(255),
  status_id                 bigint,
  constraint pk_emailbook primary key (id))
;

create table emailmessage (
  id                        bigint not null,
  emailbook_id              bigint,
  text                      varchar(255),
  eventdate                 timestamp,
  objecttype_id             bigint,
  object_id                 bigint,
  status_id                 bigint,
  constraint pk_emailmessage primary key (id))
;

create table fspathlist (
  id                        bigint not null,
  objecttype_id             integer,
  object_id                 bigint,
  path                      varchar(255),
  status_id                 bigint,
  constraint pk_fspathlist primary key (id))
;

create table groupuser (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_groupuser primary key (id))
;

create table hashlist (
  id                        bigint not null,
  code                      varchar(255),
  objecttype_id             bigint,
  object_id                 bigint,
  constraint pk_hashlist primary key (id))
;

create table itemtype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_itemtype primary key (id))
;

create table measureunit (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_measureunit primary key (id))
;

create table objecttype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_objecttype primary key (id))
;

create table paymentform (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_paymentform primary key (id))
;

create table paymenttype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_paymenttype primary key (id))
;

create table performer (
  id                        bigint not null,
  account_id                bigint,
  name                      varchar(255),
  birthday                  timestamp,
  docnumber                 varchar(255),
  issuername                varchar(255),
  issuerdate                timestamp,
  description               varchar(255),
  miscinfo                  varchar(255),
  status_id                 bigint,
  constraint pk_performer primary key (id))
;

create table phonebook (
  id                        bigint not null,
  account_id                bigint,
  codenumber                varchar(255),
  status_id                 bigint,
  constraint pk_phonebook primary key (id))
;

create table rating (
  id                        bigint not null,
  objecttype_id             integer,
  object_id                 bigint,
  evaluation                integer,
  description               varchar(255),
  status_id                 bigint,
  constraint pk_rating primary key (id))
;

create table service (
  id                        bigint not null,
  name                      varchar(255),
  status_id                 bigint,
  servicetype_id            integer,
  constraint pk_service primary key (id))
;

create table serviceblank (
  id                        bigint not null,
  account_id                bigint,
  phonebook_id              bigint,
  work_id                   bigint,
  status_id                 bigint,
  description               varchar(255),
  constraint pk_serviceblank primary key (id))
;

create table servicedeal (
  id                        bigint not null,
  serviceblank_id           bigint,
  servicelist_id            bigint,
  status_id                 bigint,
  constraint pk_servicedeal primary key (id))
;

create table servicelist (
  id                        bigint not null,
  performer_id              bigint,
  emailbook_id              bigint,
  phonebook_id              bigint,
  work_id                   bigint,
  workstage                 varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_servicelist primary key (id))
;

create table servicesheet (
  id                        bigint not null,
  objecttype_id             integer,
  object_id                 bigint,
  workitem_id               bigint,
  workvalue_id              bigint,
  value                     varchar(255),
  status_id                 bigint,
  bound_id                  integer,
  constraint pk_servicesheet primary key (id))
;

create table servicetype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_servicetype primary key (id))
;

create table smsmessage (
  id                        bigint not null,
  phonebook_id              bigint,
  serviceid                 varchar(255),
  text                      varchar(255),
  status_id                 bigint,
  eventdate                 timestamp,
  objecttype_id             integer,
  object_id                 bigint,
  constraint pk_smsmessage primary key (id))
;

create table status (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_status primary key (id))
;

create table transaction (
  transactionid             bigint not null,
  account_id                bigint,
  amount                    bigint,
  transactiontype_id        integer,
  transactionprocess_id     integer,
  servicedeal_id            bigint,
  reference                 varchar(255),
  approvalcode              varchar(255) not null,
  bankorder                 varchar(255),
  description               varchar(255),
  status_id                 bigint,
  transactionmethod_id      integer,
  constraint pk_transaction primary key (transactionid))
;

create table transactionaction (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_transactionaction primary key (id))
;

create table transactionmethod (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_transactionmethod primary key (id))
;

create table transactionprocess (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_transactionprocess primary key (id))
;

create table transactiontype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  transactionaction_id      integer,
  constraint pk_transactiontype primary key (id))
;

create table usertype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_usertype primary key (id))
;

create table weekday (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_weekday primary key (id))
;

create table weektime (
  id                        bigint not null,
  objecttype_id             integer,
  object_id                 bigint,
  weekday_id                integer,
  starttime                 time,
  endtime                   time,
  status_id                 bigint,
  constraint pk_weektime primary key (id))
;

create table work (
  id                        bigint not null,
  service_id                bigint,
  worktype_id               integer,
  name                      varchar(255),
  costfrom                  float,
  costto                    float,
  measureunit_id            integer,
  status_id                 bigint,
  constraint pk_work primary key (id))
;

create table workitem (
  id                        bigint not null,
  name                      varchar(255),
  work_id                   bigint,
  itemtype_id               integer,
  status_id                 bigint,
  constraint pk_workitem primary key (id))
;

create table worktype (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  status_id                 bigint,
  constraint pk_worktype primary key (id))
;

create table workvalue (
  id                        bigint not null,
  name                      varchar(255),
  workitem_id               bigint,
  status_id                 bigint,
  constraint pk_workvalue primary key (id))
;

create sequence account_seq;

create sequence address_seq;

create sequence bound_seq;

create sequence city_seq;

create sequence datelist_seq;

create sequence datetype_seq;

create sequence district_seq;

create sequence emailbook_seq;

create sequence emailmessage_seq;

create sequence fspathlist_seq;

create sequence groupuser_seq;

create sequence hashlist_seq;

create sequence itemtype_seq;

create sequence measureunit_seq;

create sequence objecttype_seq;

create sequence paymentform_seq;

create sequence paymenttype_seq;

create sequence performer_seq;

create sequence phonebook_seq;

create sequence rating_seq;

create sequence service_seq;

create sequence serviceblank_seq;

create sequence servicedeal_seq;

create sequence servicelist_seq;

create sequence servicesheet_seq;

create sequence servicetype_seq;

create sequence smsmessage_seq;

create sequence status_seq;

create sequence transaction_seq;

create sequence transactionaction_seq;

create sequence transactionmethod_seq;

create sequence transactionprocess_seq;

create sequence transactiontype_seq;

create sequence usertype_seq;

create sequence weekday_seq;

create sequence weektime_seq;

create sequence work_seq;

create sequence workitem_seq;

create sequence worktype_seq;

create sequence workvalue_seq;

alter table account add constraint fk_account_status_1 foreign key (status_id) references status (id);
create index ix_account_status_1 on account (status_id);
alter table account add constraint fk_account_usertype_2 foreign key (usertype_id) references usertype (id);
create index ix_account_usertype_2 on account (usertype_id);
alter table account add constraint fk_account_groupuser_3 foreign key (groupuser_id) references groupuser (id);
create index ix_account_groupuser_3 on account (groupuser_id);
alter table address add constraint fk_address_objecttype_4 foreign key (objecttype_id) references objecttype (id);
create index ix_address_objecttype_4 on address (objecttype_id);
alter table address add constraint fk_address_district_5 foreign key (district_id) references district (id);
create index ix_address_district_5 on address (district_id);
alter table address add constraint fk_address_status_6 foreign key (status_id) references status (id);
create index ix_address_status_6 on address (status_id);
alter table city add constraint fk_city_status_7 foreign key (status_id) references status (id);
create index ix_city_status_7 on city (status_id);
alter table datelist add constraint fk_datelist_objecttype_8 foreign key (objecttype_id) references objecttype (id);
create index ix_datelist_objecttype_8 on datelist (objecttype_id);
alter table datelist add constraint fk_datelist_datetype_9 foreign key (datetype_id) references datetype (id);
create index ix_datelist_datetype_9 on datelist (datetype_id);
alter table datelist add constraint fk_datelist_status_10 foreign key (status_id) references status (id);
create index ix_datelist_status_10 on datelist (status_id);
alter table datetype add constraint fk_datetype_status_11 foreign key (status_id) references status (id);
create index ix_datetype_status_11 on datetype (status_id);
alter table district add constraint fk_district_city_12 foreign key (city_id) references city (id);
create index ix_district_city_12 on district (city_id);
alter table district add constraint fk_district_status_13 foreign key (status_id) references status (id);
create index ix_district_status_13 on district (status_id);
alter table emailbook add constraint fk_emailbook_account_14 foreign key (account_id) references account (id);
create index ix_emailbook_account_14 on emailbook (account_id);
alter table emailbook add constraint fk_emailbook_status_15 foreign key (status_id) references status (id);
create index ix_emailbook_status_15 on emailbook (status_id);
alter table fspathlist add constraint fk_fspathlist_objecttype_16 foreign key (objecttype_id) references objecttype (id);
create index ix_fspathlist_objecttype_16 on fspathlist (objecttype_id);
alter table fspathlist add constraint fk_fspathlist_status_17 foreign key (status_id) references status (id);
create index ix_fspathlist_status_17 on fspathlist (status_id);
alter table groupuser add constraint fk_groupuser_status_18 foreign key (status_id) references status (id);
create index ix_groupuser_status_18 on groupuser (status_id);
alter table itemtype add constraint fk_itemtype_status_19 foreign key (status_id) references status (id);
create index ix_itemtype_status_19 on itemtype (status_id);
alter table measureunit add constraint fk_measureunit_status_20 foreign key (status_id) references status (id);
create index ix_measureunit_status_20 on measureunit (status_id);
alter table objecttype add constraint fk_objecttype_status_21 foreign key (status_id) references status (id);
create index ix_objecttype_status_21 on objecttype (status_id);
alter table paymentform add constraint fk_paymentform_status_22 foreign key (status_id) references status (id);
create index ix_paymentform_status_22 on paymentform (status_id);
alter table paymenttype add constraint fk_paymenttype_status_23 foreign key (status_id) references status (id);
create index ix_paymenttype_status_23 on paymenttype (status_id);
alter table performer add constraint fk_performer_account_24 foreign key (account_id) references account (id);
create index ix_performer_account_24 on performer (account_id);
alter table performer add constraint fk_performer_status_25 foreign key (status_id) references status (id);
create index ix_performer_status_25 on performer (status_id);
alter table phonebook add constraint fk_phonebook_account_26 foreign key (account_id) references account (id);
create index ix_phonebook_account_26 on phonebook (account_id);
alter table phonebook add constraint fk_phonebook_status_27 foreign key (status_id) references status (id);
create index ix_phonebook_status_27 on phonebook (status_id);
alter table rating add constraint fk_rating_objecttype_28 foreign key (objecttype_id) references objecttype (id);
create index ix_rating_objecttype_28 on rating (objecttype_id);
alter table rating add constraint fk_rating_status_29 foreign key (status_id) references status (id);
create index ix_rating_status_29 on rating (status_id);
alter table service add constraint fk_service_status_30 foreign key (status_id) references status (id);
create index ix_service_status_30 on service (status_id);
alter table service add constraint fk_service_servicetype_31 foreign key (servicetype_id) references servicetype (id);
create index ix_service_servicetype_31 on service (servicetype_id);
alter table serviceblank add constraint fk_serviceblank_account_32 foreign key (account_id) references account (id);
create index ix_serviceblank_account_32 on serviceblank (account_id);
alter table serviceblank add constraint fk_serviceblank_phonebook_33 foreign key (phonebook_id) references phonebook (id);
create index ix_serviceblank_phonebook_33 on serviceblank (phonebook_id);
alter table serviceblank add constraint fk_serviceblank_work_34 foreign key (work_id) references work (id);
create index ix_serviceblank_work_34 on serviceblank (work_id);
alter table serviceblank add constraint fk_serviceblank_status_35 foreign key (status_id) references status (id);
create index ix_serviceblank_status_35 on serviceblank (status_id);
alter table servicedeal add constraint fk_servicedeal_serviceblank_36 foreign key (serviceblank_id) references serviceblank (id);
create index ix_servicedeal_serviceblank_36 on servicedeal (serviceblank_id);
alter table servicedeal add constraint fk_servicedeal_servicelist_37 foreign key (servicelist_id) references servicelist (id);
create index ix_servicedeal_servicelist_37 on servicedeal (servicelist_id);
alter table servicedeal add constraint fk_servicedeal_status_38 foreign key (status_id) references status (id);
create index ix_servicedeal_status_38 on servicedeal (status_id);
alter table servicelist add constraint fk_servicelist_performer_39 foreign key (performer_id) references performer (id);
create index ix_servicelist_performer_39 on servicelist (performer_id);
alter table servicelist add constraint fk_servicelist_emailbook_40 foreign key (emailbook_id) references emailbook (id);
create index ix_servicelist_emailbook_40 on servicelist (emailbook_id);
alter table servicelist add constraint fk_servicelist_phonebook_41 foreign key (phonebook_id) references phonebook (id);
create index ix_servicelist_phonebook_41 on servicelist (phonebook_id);
alter table servicelist add constraint fk_servicelist_work_42 foreign key (work_id) references work (id);
create index ix_servicelist_work_42 on servicelist (work_id);
alter table servicelist add constraint fk_servicelist_status_43 foreign key (status_id) references status (id);
create index ix_servicelist_status_43 on servicelist (status_id);
alter table servicesheet add constraint fk_servicesheet_objecttype_44 foreign key (objecttype_id) references objecttype (id);
create index ix_servicesheet_objecttype_44 on servicesheet (objecttype_id);
alter table servicesheet add constraint fk_servicesheet_workitem_45 foreign key (workitem_id) references workitem (id);
create index ix_servicesheet_workitem_45 on servicesheet (workitem_id);
alter table servicesheet add constraint fk_servicesheet_workvalue_46 foreign key (workvalue_id) references workvalue (id);
create index ix_servicesheet_workvalue_46 on servicesheet (workvalue_id);
alter table servicesheet add constraint fk_servicesheet_status_47 foreign key (status_id) references status (id);
create index ix_servicesheet_status_47 on servicesheet (status_id);
alter table servicesheet add constraint fk_servicesheet_bound_48 foreign key (bound_id) references bound (id);
create index ix_servicesheet_bound_48 on servicesheet (bound_id);
alter table servicetype add constraint fk_servicetype_status_49 foreign key (status_id) references status (id);
create index ix_servicetype_status_49 on servicetype (status_id);
alter table transaction add constraint fk_transaction_account_50 foreign key (account_id) references account (id);
create index ix_transaction_account_50 on transaction (account_id);
alter table transaction add constraint fk_transaction_transactiontyp_51 foreign key (transactiontype_id) references transactiontype (id);
create index ix_transaction_transactiontyp_51 on transaction (transactiontype_id);
alter table transaction add constraint fk_transaction_transactionpro_52 foreign key (transactionprocess_id) references transactionprocess (id);
create index ix_transaction_transactionpro_52 on transaction (transactionprocess_id);
alter table transaction add constraint fk_transaction_status_53 foreign key (status_id) references status (id);
create index ix_transaction_status_53 on transaction (status_id);
alter table transaction add constraint fk_transaction_transactionmet_54 foreign key (transactionmethod_id) references transactionmethod (id);
create index ix_transaction_transactionmet_54 on transaction (transactionmethod_id);
alter table transactionaction add constraint fk_transactionaction_status_55 foreign key (status_id) references status (id);
create index ix_transactionaction_status_55 on transactionaction (status_id);
alter table transactionmethod add constraint fk_transactionmethod_status_56 foreign key (status_id) references status (id);
create index ix_transactionmethod_status_56 on transactionmethod (status_id);
alter table transactionprocess add constraint fk_transactionprocess_status_57 foreign key (status_id) references status (id);
create index ix_transactionprocess_status_57 on transactionprocess (status_id);
alter table transactiontype add constraint fk_transactiontype_status_58 foreign key (status_id) references status (id);
create index ix_transactiontype_status_58 on transactiontype (status_id);
alter table transactiontype add constraint fk_transactiontype_transactio_59 foreign key (transactionaction_id) references transactionaction (id);
create index ix_transactiontype_transactio_59 on transactiontype (transactionaction_id);
alter table usertype add constraint fk_usertype_status_60 foreign key (status_id) references status (id);
create index ix_usertype_status_60 on usertype (status_id);
alter table weektime add constraint fk_weektime_objecttype_61 foreign key (objecttype_id) references objecttype (id);
create index ix_weektime_objecttype_61 on weektime (objecttype_id);
alter table weektime add constraint fk_weektime_weekday_62 foreign key (weekday_id) references weekday (id);
create index ix_weektime_weekday_62 on weektime (weekday_id);
alter table weektime add constraint fk_weektime_status_63 foreign key (status_id) references status (id);
create index ix_weektime_status_63 on weektime (status_id);
alter table work add constraint fk_work_service_64 foreign key (service_id) references service (id);
create index ix_work_service_64 on work (service_id);
alter table work add constraint fk_work_worktype_65 foreign key (worktype_id) references worktype (id);
create index ix_work_worktype_65 on work (worktype_id);
alter table work add constraint fk_work_measureunit_66 foreign key (measureunit_id) references measureunit (id);
create index ix_work_measureunit_66 on work (measureunit_id);
alter table work add constraint fk_work_status_67 foreign key (status_id) references status (id);
create index ix_work_status_67 on work (status_id);
alter table workitem add constraint fk_workitem_work_68 foreign key (work_id) references work (id);
create index ix_workitem_work_68 on workitem (work_id);
alter table workitem add constraint fk_workitem_itemtype_69 foreign key (itemtype_id) references itemtype (id);
create index ix_workitem_itemtype_69 on workitem (itemtype_id);
alter table workitem add constraint fk_workitem_status_70 foreign key (status_id) references status (id);
create index ix_workitem_status_70 on workitem (status_id);
alter table worktype add constraint fk_worktype_status_71 foreign key (status_id) references status (id);
create index ix_worktype_status_71 on worktype (status_id);
alter table workvalue add constraint fk_workvalue_workitem_72 foreign key (workitem_id) references workitem (id);
create index ix_workvalue_workitem_72 on workvalue (workitem_id);
alter table workvalue add constraint fk_workvalue_status_73 foreign key (status_id) references status (id);
create index ix_workvalue_status_73 on workvalue (status_id);



# --- !Downs

drop table if exists account cascade;

drop table if exists address cascade;

drop table if exists bound cascade;

drop table if exists city cascade;

drop table if exists datelist cascade;

drop table if exists datetype cascade;

drop table if exists district cascade;

drop table if exists emailbook cascade;

drop table if exists emailmessage cascade;

drop table if exists fspathlist cascade;

drop table if exists groupuser cascade;

drop table if exists hashlist cascade;

drop table if exists itemtype cascade;

drop table if exists measureunit cascade;

drop table if exists objecttype cascade;

drop table if exists paymentform cascade;

drop table if exists paymenttype cascade;

drop table if exists performer cascade;

drop table if exists phonebook cascade;

drop table if exists rating cascade;

drop table if exists service cascade;

drop table if exists serviceblank cascade;

drop table if exists servicedeal cascade;

drop table if exists servicelist cascade;

drop table if exists servicesheet cascade;

drop table if exists servicetype cascade;

drop table if exists smsmessage cascade;

drop table if exists status cascade;

drop table if exists transaction cascade;

drop table if exists transactionaction cascade;

drop table if exists transactionmethod cascade;

drop table if exists transactionprocess cascade;

drop table if exists transactiontype cascade;

drop table if exists usertype cascade;

drop table if exists weekday cascade;

drop table if exists weektime cascade;

drop table if exists work cascade;

drop table if exists workitem cascade;

drop table if exists worktype cascade;

drop table if exists workvalue cascade;

drop sequence if exists account_seq;

drop sequence if exists address_seq;

drop sequence if exists bound_seq;

drop sequence if exists city_seq;

drop sequence if exists datelist_seq;

drop sequence if exists datetype_seq;

drop sequence if exists district_seq;

drop sequence if exists emailbook_seq;

drop sequence if exists emailmessage_seq;

drop sequence if exists fspathlist_seq;

drop sequence if exists groupuser_seq;

drop sequence if exists hashlist_seq;

drop sequence if exists itemtype_seq;

drop sequence if exists measureunit_seq;

drop sequence if exists objecttype_seq;

drop sequence if exists paymentform_seq;

drop sequence if exists paymenttype_seq;

drop sequence if exists performer_seq;

drop sequence if exists phonebook_seq;

drop sequence if exists rating_seq;

drop sequence if exists service_seq;

drop sequence if exists serviceblank_seq;

drop sequence if exists servicedeal_seq;

drop sequence if exists servicelist_seq;

drop sequence if exists servicesheet_seq;

drop sequence if exists servicetype_seq;

drop sequence if exists smsmessage_seq;

drop sequence if exists status_seq;

drop sequence if exists transaction_seq;

drop sequence if exists transactionaction_seq;

drop sequence if exists transactionmethod_seq;

drop sequence if exists transactionprocess_seq;

drop sequence if exists transactiontype_seq;

drop sequence if exists usertype_seq;

drop sequence if exists weekday_seq;

drop sequence if exists weektime_seq;

drop sequence if exists work_seq;

drop sequence if exists workitem_seq;

drop sequence if exists worktype_seq;

drop sequence if exists workvalue_seq;


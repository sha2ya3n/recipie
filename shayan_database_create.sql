create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description longtext, recipie_id bigint, unit_of_measure_id bigint, primary key (id)) engine=InnoDB;
create table note (id bigint not null auto_increment, recipe_notes longtext, recipie_id bigint, primary key (id)) engine=InnoDB;
create table recipie (id bigint not null auto_increment, cook_time integer, description longtext, difficulty varchar(255), direction longtext, image longblob, prep_time integer, rating varchar(255), source varchar(255), url varchar(255), note_id bigint, primary key (id)) engine=InnoDB;
create table recipie_category (recepie_id bigint not null, category_id bigint not null, primary key (recepie_id, category_id)) engine=InnoDB;
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), ingredient_id bigint, primary key (id)) engine=InnoDB;
alter table ingredient add constraint FKbtvncyw8n7p8du27m2a750mlk foreign key (recipie_id) references recipie (id);
alter table ingredient add constraint FK15ttfoaomqy1bbpo251fuidxw foreign key (unit_of_measure_id) references unit_of_measure (id);
alter table note add constraint FK521f98mtyrjv7fpklg65mpahk foreign key (recipie_id) references recipie (id);
alter table recipie add constraint FK2hcmr7dvwi6xdpk27rx1sw2it foreign key (note_id) references note (id);
alter table recipie_category add constraint FKkpw8i0si7sjqsu24tvxvw3c4t foreign key (category_id) references category (id);
alter table recipie_category add constraint FKc94p1lm05hos5fncirhsjkwud foreign key (recepie_id) references recipie (id);
alter table unit_of_measure add constraint FKsuvp4jw48bi3h82hkxrfbgfqu foreign key (ingredient_id) references ingredient (id);

﻿drop table s_users;
drop table s_roles;
drop table s_user_role;

create table s_users(
    id varchar(36) primary key,
    username varchar(30) not null,
    password varchar(255) not null,
    active boolean
);

insert into s_users(id, username, password, active)
values('u001', 'pardi', '123', true);
insert into s_users(id, username, password, active)
values('u002', 'adi', '123', true);

create table s_roles(
    id varchar(36) primary key,
    nama varchar(255) not null
);

insert into s_roles(id, nama)
values('adm', 'ROLE_ADMIN');
insert into s_roles(id, nama)
values('stf', 'ROLE_STAFF');

create table s_user_role(
    id_user varchar(36) not null,
    id_role varchar(36) not null
);

insert into s_user_role(id_user, id_role)
values('u001', 'adm');
insert into s_user_role(id_user, id_role)
values('u001', 'stf');
insert into s_user_role(id_user, id_role)
values('u002', 'stf');

select username, password, active as enable from s_users where username='pardi';

select u.username, r.nama as authority 
from s_users u 
join s_user_role ur on u.id=ur.id_user 
join s_roles r on ur.id_role=r.id
where u.username='pardi';
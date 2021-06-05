create
database gdufcp;
use
gdufcp;

-- 用户
create table user
(
    sno varchar(20) primary key,
    username varchar(20),
    password varchar(20),
    sex      varchar(3),
    age      int,
    height   double,
    weight   double,
    personalProfile text,
    contactInformation text,
    status   int,
    check (sex in ('男', '女', '?'))
);

drop table user;
-- 好友列表
create table friendList
(
    user1no varchar(20),
    user2no varchar(20),
    addtime long,
    primary key (user1no, user2no)
);

-- 好友请求
create table friendreq
(
    id      int primary key auto_increment,
    reqno   varchar(20),
    resno   varchar(20),
    reqtime long,
    status  int
);

-- 配对请求
create table pairingreq
(
    id      int primary key auto_increment,
    reqsno  varchar(20),
    ressno  varchar(20),
    reqtime long,
    content text,
    status  int
);

-- 反馈
create table feedback
(
    id      int primary key auto_increment,
    sno     varchar(20),
    type    int,
    content text,
    ftime   long
);

-- 管理员
create table admin
(
    num      varchar(20),
    password varchar(20)
);

show
tables;

insert into user(sno, username, password, sex) values('191543132', 'ycx', '123', '?');
insert into user(sno, username, password) values('191543110', 'cht', '123');
insert into user(sno, username, password) values('191543120', 'lj', '123');
insert into user(sno, username, password) values('191543105', 'lzx', '123');

insert into friendlist(user1no, user2no) values('191543105', '191543132');
insert into friendlist(user1no, user2no) values('191543110', '191543132');
insert into friendlist(user1no, user2no) values('191543120', '191543132');
insert into friendlist(user1no, user2no) values('191543110', '191543120');

select if(user1no = '191543132', user2no, user1no) as sno
from friendlist
where user1no = '191543132' or user2no = '191543132';

alter table user add column personalProfile text;

alter table user add column contactInformation text;

alter table user add column age int;
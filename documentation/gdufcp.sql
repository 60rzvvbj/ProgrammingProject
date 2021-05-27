create
database gdufcp;
use
gdufcp;

-- 用户
create table user
(
    sno      int primary key auto_increment,
    username varchar(20),
    password varchar(20),
    sex      varchar(2),
    height   double,
    weight   double,
    status   int,
    check (sex in ('男', '女'))
);

-- 好友列表
create table friendList
(
    user1no int,
    user2no int,
    addtime long,
    primary key (user1no, user2no)
);

-- 好友请求
create table friendreq
(
    id      int primary key auto_increment,
    reqno   int,
    resno   int,
    reqtime long,
    status  int
);

-- 配对请求
create table pairingreq
(
    id      int primary key auto_increment,
    reqsno  int,
    ressno  int,
    reqtime long,
    content text,
    status  int
);

-- 反馈
create table feedback
(
    id      int primary key auto_increment,
    sno     int,
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
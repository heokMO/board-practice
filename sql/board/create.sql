create table board
(
    id          smallint unsigned not null AUTO_INCREMENT primary key,
    name        varchar(40)       not null,
    login_req   tinyint           not null,
    description varchar(255)
);
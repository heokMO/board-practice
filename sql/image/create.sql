create table image
(
    id          bigint unsigned not null AUTO_INCREMENT primary key,
    post_id     bigint unsigned,
    origin_name varchar(256),
    path varchar(300)
);

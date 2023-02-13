create table image
(
    id          bigint unsigned not null AUTO_INCREMENT primary key,
    post_id     bigint unsigned not null,
    origin_name varchar(256),
    path varchar(300)
);
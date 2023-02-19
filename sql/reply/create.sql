create table reply
(
    id          bigint unsigned not null AUTO_INCREMENT primary key,
    post_id     bigint unsigned not null,
    content varchar(255) not null,
    written_user int unsigned,
    written_time    timestamp   not null default current_timestamp,
    update_time     timestamp   not null default current_timestamp,
    non_mem_nick varchar(24),
    non_mem_pw varchar(20)
);